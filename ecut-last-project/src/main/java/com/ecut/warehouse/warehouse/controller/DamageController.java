package com.ecut.warehouse.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.DamageService;
import com.ecut.warehouse.warehouse.service.GoodsService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/damage")
public class DamageController {

    @Autowired
    private DamageService damageService;

    @Autowired
    private GoodsService goodsService;

    //新增报损单
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> damage( @RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        List<JSONObject> idsFirst = null;
        List<JSONObject> idsLast = new ArrayList<>();
        Damage damage = new Damage();
        //2.封装Damage
        idsFirst = (List<JSONObject>)jsonObject.get("ids");

        Goods goods = new Goods();
        Goods restGoods = new Goods();
        JSONObject idsJson = new JSONObject();
        for(int i = 0;i<idsFirst.size();i++){
            goods.setGoodsCode(idsFirst.get(i).get("goodsCode").toString());
            goods.setSpecificationItems(idsFirst.get(i).get("specificationItems").toString());
            restGoods = goodsService.getGoodsByCondition(goods).get(0);
            idsJson.put("id",restGoods.getId());
            idsJson.put("count",idsFirst.get(i).get("count").toString());
            idsLast.add(idsJson);
        }

        try{
            damage = loadDamage(jsonObject, damage);
            damage.setId(CommonUtils.getUUID());
        }catch (Exception e){
            log.error("===============时间异常"+e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.PARA_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        if(null != jsonObject.get("comment")){
            damage.setComment(jsonObject.get("comment").toString());
        }
        //3.上报报损单
        try{
            if(null != idsLast && 0 != idsLast.size()){
                damageService.insertDamage(idsLast, damage);
            }else{
                returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.PARA_ERROR);
            }
        }catch (Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
        }
        //4.返回结果
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    //查询报损单
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseEntity<String> query( @RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Damage damage =new Damage();
        List<Damage> damages = null;
        //2.查询
        //2.1设置查询实体
        try{
            damage = loadDamage(jsonObject, damage);
            damage.setCreateTime(null);
        }catch(Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //2.2查询
        try{
            damages = damageService.selectAllDamages(damage);
        }catch (Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        System.out.println(damages);
        returnJson.put("damages",damages);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }

    //根据主键查询报损单
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public ResponseEntity<String> queryById( @RequestBody JSONObject jsonObject){
        String damageId = jsonObject.get("damageId").toString();
        //1.定义全局变量
        Damage damage = null;
        List<Goods> goods = null;
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        //2.根据主键查询
        try{
            damage = damageService.queryById(damageId);
            goods = damageService.queryGoodsById(damageId);
        }catch(Exception e){
            log.error("========================单查失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        returnJson.put("damage", damage);
        returnJson.put("goods", goods);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }

    //更改报损单状态
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> udateStatus( @RequestBody Damage damage){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        //2.判断必要参数是否为空
        if(null == damage.getId() || "".equals(damage.getId()) || null == damage.getStatus()){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.PARA_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //3.判断该报损单是否存在
        try{
            Damage tmpDamage = damageService.queryById(damage.getId());
            if(9999 != tmpDamage.getStatus()){
                //4.更新状态
                damageService.updateStatus(damage.getId(), damage.getStatus());
            }else{
                returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.UPDATE_ERROR);
                log.error("========================数据不存在");
            }
        }catch(Exception e){
            log.error("========================更新状态失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //5.返回值处理
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    //更改报损单信息
    @RequestMapping(value = "/updateDamage", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> udateDamage( @RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Damage damage =new Damage();
        //2.封装实体
        try{
            damage = loadDamage(jsonObject, damage);
        }catch(Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        List<JSONObject> ids = (List<JSONObject>) jsonObject.get("ids");
        //3.修改信息
        //3.1修改商品列表
        damageService.updateDamage(damage, ids);
        //3.2修改damage信息
        //4.处理返回值
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    public Damage loadDamage(JSONObject jsonObject, Damage damage) throws Exception{
        if(null != jsonObject.get("deep") && !"".equals(jsonObject.get("deep").toString()))
            damage.setDeep(jsonObject.get("deep").toString());
        if(null != jsonObject.get("damageName") && !"".equals(jsonObject.get("damageName").toString()))
            damage.setDamageName(jsonObject.get("damageName").toString());
        if(null != jsonObject.get("damageType") && !"".equals(jsonObject.get("damageType").toString()))
            damage.setDamageType(jsonObject.get("damageType").toString());
        damage.setCreateTime(new Date());
        if(null != jsonObject.get("id") && !"".equals(jsonObject.get("id").toString()))
            damage.setId(jsonObject.get("id").toString());
        if(null != jsonObject.get("comment") && !"".equals(jsonObject.get("comment").toString()))
            damage.setComment(jsonObject.get("comment").toString());
        if(null != jsonObject.get("status"))
            damage.setStatus((Integer)jsonObject.get("status"));
        if(null != jsonObject.get("createPersonId") && !"".equals(jsonObject.get("createPersonId").toString()))
            damage.setCreatePersonId(jsonObject.get("createPersonId").toString());
        if(null != jsonObject.get("checkPersonId") && !"".equals(jsonObject.get("checkPersonId").toString()))
            damage.setCheckPersonId(jsonObject.get("checkPersonId").toString());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+damage);
        return damage;
    }

}
