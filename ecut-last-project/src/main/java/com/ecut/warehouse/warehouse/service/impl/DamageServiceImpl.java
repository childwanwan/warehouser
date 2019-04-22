package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.DamageDao;
import com.ecut.warehouse.warehouse.dao.GoodsDao;
import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.DamageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DamageServiceImpl implements DamageService {

    @Autowired
    DamageDao damageDao;

    @Autowired
    GoodsDao goodsDao;

    @Override
    @Transactional
    public void insertDamage(List<JSONObject> goodsId, Damage damage) {
        for(JSONObject id: goodsId){
            System.out.println("============"+id.get("id").toString()+"============"+damage.getId());
            damageDao.insertGoodDamageRel(id.get("id").toString(), damage.getId(), Integer.parseInt(id.get("count").toString()));
        }
        //System.out.println("========================中间表插入完毕");
        damageDao.insertDamage(damage);
    }

    @Override
    public List<Damage> selectAllDamages(Damage damage) {
        return damageDao.selectAllDamages(damage);
    }

    @Override
    public Damage queryById(String damageId) {
        return damageDao.queryById(damageId);
    }

    @Override
    public List<Goods> queryGoodsById(String damageId) {
        return damageDao.queryGoodsById(damageId);
    }

    @Override
    @Transactional
    public void updateStatus(String damageId, Integer status) {
        //只要状态吗为2，代表审核通过，商品数量减少
        List<String> goodsId =null;
        damageDao.updateStatus(damageId, status);
        if(2 == status){
            //添加审核时间
            damageDao.updateDamage(new Damage().setApprovalTime(new Date()));
            //查找关联的商品id
            goodsId = damageDao.selectGoodsId(damageId);
            for(String goodId: goodsId){
                goodsDao.updateGoods(new Goods().setGoodsNum(damageDao.findCount(damageId, goodId)).setId(goodId));
            }
        }
    }

    @Override
    @Transactional
    public void updateDamage(Damage damage, List<JSONObject> ids) {
        //1.先删除原来的之间表关系
        damageDao.deleteRelById(damage.getId());
        //2.重新建立中间表联系
        if (null !=ids &&"".equals(ids)&&ids.size()>0) {
            for (JSONObject id : ids) {
                damageDao.insertGoodDamageRel(id.get("id").toString(), damage.getId(), (Integer) id.get("count"));
            }
        }
        //3.更新报损单信息
        damageDao.updateDamage(damage);
    }
}
