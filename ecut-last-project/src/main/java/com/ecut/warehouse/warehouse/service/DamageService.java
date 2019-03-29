package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.entity.Goods;
import net.sf.json.JSONObject;

import java.util.List;

public interface DamageService {

    void insertDamage(List<JSONObject> goodsId, Damage damage);

    List<Damage> selectAllDamages(Damage damage);

    Damage queryById(String damageId);

    List<Goods> queryGoodsById(String damageId);

    void updateStatus(String damageId, Integer status);

    void updateDamage(Damage damage, List<JSONObject> ids);

}
