package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.service.DamageService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/damage")
public class DamageController {

    @Autowired
    private DamageService damageService;

    //新增报损单
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> damage( @RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        List<JSONObject> ids = null;
        Damage damage = new Damage();
        //2.封装Damage
        ids = (List<JSONObject>)jsonObject.get("ids");
        damage.setId(CommonUtils.getUUID()).setCreatePersonId(jsonObject.get("createPersonId").toString())
                .setCheckPersonId(jsonObject.get("checkPersonId").toString())
                .setDamageName(jsonObject.get("damageName").toString())
                .setCreateTime(new Date()).setStatus(1);
        if(null != jsonObject.get("comment")){
            damage.setComment(jsonObject.get("comment").toString());
        }
        //3.上报报损单
        try{
            if(null != ids && 0 != ids.size()){
                damageService.insertDamage(ids, damage);
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
    public ResponseEntity<JSONObject> query( @RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Damage damage =new Damage();
        List<Damage> damages = null;
        //2.查询
        //2.1设置查询实体
        if(null != jsonObject.get("deep") && !"".equals(jsonObject.get("deep")))
            damage.setDeep(jsonObject.get("deep").toString());
        if(null != jsonObject.get("damageName") && !"".equals(jsonObject.get("damageName")))
            damage.setDamageName(jsonObject.get("damageName").toString());
        if(null != jsonObject.get("damageType") && !"".equals(jsonObject.get("damageType")))
            damage.setDamageType(jsonObject.get("damageType").toString());
        try{
            if(null != jsonObject.get("createTime"))
                damage.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.get("createTime").toString()));
            if(null != jsonObject.get("approvalTime"))
                damage.setApprovalTime(new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.get("approvalTime").toString()));
        }catch (Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        if(null != jsonObject.get("comment") && !"".equals(jsonObject.get("comment")))
            damage.setComment(jsonObject.get("comment").toString());
        if(null != jsonObject.get("status"))
            damage.setStatus((Integer)jsonObject.get("status"));
        if(null != jsonObject.get("createPersonId") && !"".equals(jsonObject.get("createPersonId")))
            damage.setCreatePersonId(jsonObject.get("createPersonId").toString());
        if(null != jsonObject.get("checkPersonId") && !"".equals(jsonObject.get("checkPersonId")))
            damage.setCheckPersonId(jsonObject.get("checkPersonId").toString());
        //2.2查询
        try{
            damages = damageService.selectAllDamages(damage);
        }catch (Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        System.out.println(damages);
        returnJson.element("damages", damages.toString());
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

//    //根据主键查询报损单
//    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
//    public ResponseEntity<JSONObject> queryById( @RequestBody JSONObject jsonObject){
//    }
//
//    //更改报损单状态
//    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
//    public ResponseEntity<JSONObject> udateStatus( @RequestBody JSONObject jsonObject){
//        //1.定义全局变量
//    }

}
