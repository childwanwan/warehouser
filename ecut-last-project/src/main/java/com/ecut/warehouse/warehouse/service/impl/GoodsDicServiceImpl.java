package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.GoodsDicDao;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GoodsDicServiceImpl implements GoodsDicService {

    @Autowired
    GoodsDicDao goodsDicDao;

    @Override
    public Goods selectByMap(Map<String, Object> map) {
        return goodsDicDao.selectByMap(map);
    }

    @Override
    public void insert(Goods good) {
        goodsDicDao.insert(good);
    }

    @Override
    public void deleteByGoodsCode(String goodsCode) {
        goodsDicDao.deleteByGoodsCode(goodsCode);
    }

    @Override
    public List<Goods> selectAll(Goods good) {
        return goodsDicDao.selectAll(good);
    }

}
