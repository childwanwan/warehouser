package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface GoodsDicDao {

    Goods selectByMap(Map<String, Object> map);

    void insert(Goods good);

    void deleteByGoodsCode(String goodsCode);

    List<Goods> selectAll(Goods good);


}
