package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsDicService {

    Goods selectByMap(Map<String, Object> map);

    void insert(Goods good);

    void deleteByGoodsCode(String goodsCode);

    List<Goods> selectAll(Goods good);


}
