package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.entity.Instore;
import net.sf.json.JSONObject;

import java.util.List;

public interface InstoreService {
    void deleteById(String instoreId);

    Instore selectById(String instoreId);

    List<Instore> selectAll(Instore instore);

    void insert(Instore instore, List<JSONObject> goodsId);
}
