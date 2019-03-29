package com.ecut.warehouse.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsDicService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            //3.新增
            try{
                good.setId(CommonUtils.getUUID());
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

    @GetMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestParam String goodCode){
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
        //3.返回值处理
        returnJson.put("goods", goods.toString());
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }



}
