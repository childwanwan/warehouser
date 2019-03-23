package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Damage;
import net.sf.json.JSONObject;

import java.util.List;

public interface DamageService {

    void insertDamage(List<JSONObject> goodsId, Damage damage);

    List<Damage> selectAllDamages(Damage damage);

}
