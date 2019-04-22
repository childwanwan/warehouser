package com.ecut.warehouse.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ecut.warehouse.warehouse.domain.DoChangFunction;
import com.ecut.warehouse.warehouse.domain.GoodsDireForm;
import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsDicService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanhq@si-tech.com.cn
 * @Description: xx相关
 * @Copyright @SI-TECH 2019. All rights reserved
 * @date 2019-xx-xx
 * @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/goodsDic")
public class GoodsDicController {

    @Autowired
    GoodsDicService goodsDicService;

    //增加 删除 查询（单、全）

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody Goods good){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Goods tmpGood = null;
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("goodsCode", good.getGoodsCode());
        //2.查询插入的商品是否存在（根据商品编号）
        tmpGood = goodsDicService.selectByMap(condition);
        if(null == tmpGood || 9999 == tmpGood.getStatus()){

            if (null != good.getSpecificationItems() && !"".equals(good.getSpecificationItems())){
                String s = good.getSpecificationItems();
                //[a,c,d]
                String t = s.substring(1,s.length()-1);
                //System.out.println();
                String[]  strs=t.split(",");
                //System.out.println(strs);
                good.setSpecificationItems(JSONArray.fromObject(strs).toString());
            }

            //3.新增
            try{
                good.setId(CommonUtils.getUUID());
                good.setGoodsNum(1);
                //good.setSpecificationItems()
                goodsDicService.insert(good);
            }catch(Exception e){
                log.error("========================商品字典新增失败"+ e.getMessage());
                returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
                return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
            }
        }else{
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //4.返回值处理
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody JSONObject jsonObject ){
        String goodCode = jsonObject.get("goodCode").toString();
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        //2.删除
        try{
            goodsDicService.deleteByGoodsCode(goodCode);
        }catch(Exception e){
            log.error("========================商品字典删除失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    @GetMapping("/queryById")
    public ResponseEntity<String> queryById(@RequestParam String id){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        Goods good = null;
        //2.查询单个
        try{
            good = goodsDicService.selectByMap(condition);
        }catch(Exception e){
            log.error("========================商品字典查询失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        returnJson.put("good", good);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }

    @PostMapping("/query")
    public ResponseEntity<String> queryByMap(@RequestBody Goods good){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        List<Goods> goods = null;
        //2.查询单个
        try{
            goods = goodsDicService.selectAll(good);
        }catch(Exception e){
            log.error("========================商品字典查询失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        List<GoodsDireForm> list = new ArrayList<>();
        if (null!=goods&&goods.size()>0){
            GoodsDireForm goodsDireForm = new GoodsDireForm();
            for (int i =0 ;i<goods.size();i++){
                goodsDireForm = DoChangFunction.goodsChangeToGoodsDireForm(goods.get(i));
                list.add(goodsDireForm);
            }
        }

        //3.返回值处理
        returnJson.put("data", list);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }



    @PostMapping("/update")
    public ResponseEntity<JSONObject> update(@RequestBody Goods good){
        //定义返回的json
        JSONObject returnJson = new JSONObject();

        if (null == good.getGoodsNum() || "".equals(good.getGoodsNum())) {
            good.setGoodsNum(1);
        }
        if (null == good.getStatus()) {
            good.setStatus(1);
        }
        if (null != good.getSpecificationItems() && !"".equals(good.getSpecificationItems())){
            String s = good.getSpecificationItems();
            //[a,c,d]
            String t = s.substring(1,s.length()-1);
            //System.out.println();
            String[]  strs=t.split(",");
            //System.out.println(strs);
            good.setSpecificationItems(JSONArray.fromObject(strs).toString());
        }
        //System.out.println(good);
        int i = 0;
        try {
            i = goodsDicService.update(good);
        } catch (Exception e) {
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
        }
        if (i > 0) {
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        }
        //获取参数，及将参数封装成对象
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }



}
