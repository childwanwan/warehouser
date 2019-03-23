package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.DamageDao;
import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.service.DamageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DamageServiceImpl implements DamageService {

    @Autowired
    DamageDao damageDao;

    @Override
    @Transactional
    public void insertDamage(List<JSONObject> goodsId, Damage damage) {
        for(JSONObject id: goodsId){
            System.out.println("============"+id.get("id").toString()+"============"+damage.getId());
            damageDao.insertGoodDamageRel(id.get("id").toString(), damage.getId());
        }
        System.out.println("========================中间表插入完毕");
        damageDao.insertDamage(damage);
    }

    @Override
    public List<Damage> selectAllDamages(Damage damage) {
        return damageDao.selectAllDamages(damage);
    }
}
