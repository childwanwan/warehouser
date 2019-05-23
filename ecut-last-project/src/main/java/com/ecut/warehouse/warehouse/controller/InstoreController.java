package com.ecut.warehouse.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Instore;
import com.ecut.warehouse.warehouse.entity.InstoreItems;
import com.ecut.warehouse.warehouse.service.InstoreItemsService;
import com.ecut.warehouse.warehouse.service.InstoreService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yanhq@si-tech.com.cn
 * @Description: xx相关
 * @Copyright @SI-TECH 2019. All rights reserved
 * @date 2019-xx-xx
 * @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/instore")
public class InstoreController {
    @Autowired
    private InstoreItemsService instoreItemsService;

    @Autowired
    InstoreService instoreService;

    //增、删、查

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody JSONObject jsonObject){
        //System.out.println(jsonObject);
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Instore instore = new Instore();
        List<JSONObject> goodsId = (List<JSONObject>) jsonObject.get("goodsList");
        //2.封装实体
        instore = loadInstore(jsonObject, instore);
        //3.新增
            instoreService.insert(instore.setId(CommonUtils.getUUID()), goodsId);
        try{}catch(Exception e){
            log.error("========================入库单新增失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //4.返回值处理
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody JSONObject jsonObject){
        String instoreId = jsonObject.get("instoreId").toString();
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        //2.删除
        try{
            instoreService.deleteById(instoreId);
        }catch(Exception e){
            log.error("========================入库单删除失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

    @PostMapping("/queryById")
    public ResponseEntity<String> queryById(@RequestBody JSONObject jsonObject ){
        String instoreId = jsonObject.get("instoreId").toString();
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Instore instore = null;
        //2.查询单个
        try{
            instore = instoreService.selectById(instoreId);
        }catch(Exception e){
            log.error("========================入库单查询失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        returnJson.put("instore", instore);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }

    @PostMapping("/query")
    public ResponseEntity<String> queryByMap(@RequestBody JSONObject jsonObject){
        //1.定义全局变量
        JSONObject returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
        Instore instore = new Instore();
        instore = loadInstore(jsonObject, instore);
        instore.setInstoreTime(null);
        List<Instore> instores = null;
        //2.查询单个
        try{
            instores = instoreService.selectAll(instore);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
//
//            for (int i = 0;i<instores.size();i++){
//                instores.get(i).setInstoreTime(DateUtil.parse(simpleDateFormat.format(instores.get(i).getInstoreTime()),"yyyy-MM-dd HH:mm:ss"));
//            }
        }catch(Exception e){
            log.error("========================入库单查询失败"+ e.getMessage());
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
            return new ResponseEntity<>(returnJson.toString(), HttpStatus.ACCEPTED);
        }
        //3.返回值处理
        returnJson.put("instores", instores);
        return new ResponseEntity<>(JSON.toJSONString(returnJson), HttpStatus.ACCEPTED);
    }

    public Instore loadInstore(JSONObject jsonObject, Instore instore) throws RuntimeException{
        if(null != jsonObject.get("provideId") && !"".equals(jsonObject.get("provideId").toString()))
            instore.setProvideId(jsonObject.get("provideId").toString());
        if(null != jsonObject.get("totalNum"))
            instore.setTotalNum((Integer) jsonObject.get("totalNum"));
        instore.setInstoreTime(new Date());
        if(null != jsonObject.get("reserverId") && !"".equals(jsonObject.get("reserverId").toString()))
            instore.setReserverId(jsonObject.get("reserverId").toString());
        if(null != jsonObject.get("status"))
            instore.setStatus((Integer)jsonObject.get("status"));
        return instore;
    }


    @RequestMapping(value = "/getInstoresGoodsByInstoresId", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> getInstoresGoodsByInstoresId(@RequestBody JSONObject jsonObject) {

        String id = jsonObject.get("instoreId").toString();
        //定义返回的json
        JSONObject returnJson = new JSONObject();

        InstoreItems paraInstoreItems = new InstoreItems();
        paraInstoreItems.setInstoreId(id);


        List<InstoreItems> returnList = new ArrayList<>();

        try {
            returnList = instoreItemsService.getInstoresGoodsByInstoresId(paraInstoreItems);

            //System.out.println(returnList);
        }catch (Exception e){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
        }

        if (null!=returnList&&!"".equals(returnList)){
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
            returnJson.put("data",returnList);
        }else {
            returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
        }

        //获取参数，及将参数封装成对象
        return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
    }

}
