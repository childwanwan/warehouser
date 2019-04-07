package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.GoodsDao;
import com.ecut.warehouse.warehouse.dao.GoodsDicDao;
import com.ecut.warehouse.warehouse.dao.InstoreDao;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.entity.Instore;
import com.ecut.warehouse.warehouse.service.InstoreService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class InstoreServiceImpl implements InstoreService {


    @Autowired
    InstoreDao instoreDao;

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    GoodsDicDao goodsDicDao;

    @Override
    public void deleteById(String instoreId) {
        instoreDao.deleteById(instoreId);
    }

    @Override
    public Instore selectById(String instoreId) {
        return instoreDao.selectById(instoreId);
    }

    @Override
    public List<Instore> selectAll(Instore instore) {
        return instoreDao.selectAll(instore);
    }

    @Override
    @Transactional
    public void insert(Instore instore, List<JSONObject> goods) {
        instoreDao.insertInstore(instore);
        Map<String, Object> mapRel = null;
        Map<String, Object> mapGood = null;
        String id = null;
        for (JSONObject good: goods){
            id = CommonUtils.getUUID();
            mapRel = new HashMap<String, Object>();
            mapGood = new HashMap<String, Object>();
            mapRel.put("id", CommonUtils.getUUID());
            mapRel.put("instoreId",instore.getId());
            mapRel.put("goodsId", id);
            mapRel.put("buyNum", good.get("goodsNum"));
            mapRel.put("comment", good.get("comment"));
            System.out.println(mapRel);
            instoreDao.insertRel(mapRel);
            mapGood.put("id", good.get("id"));

            Goods goods1 = goodsDicDao.selectByMap(mapGood);
            //System.out.println(good);
            //System.out.println(good.get("specificationItems").toString());
            goods1.setSpecificationItems(good.get("specificationItems").toString());
            goods1.setGoodsNum(Integer.parseInt( good.get("goodsNum").toString()));
            goods1.setId(id);
            goodsDao.addGoods(goods1);
        }
    }
}
