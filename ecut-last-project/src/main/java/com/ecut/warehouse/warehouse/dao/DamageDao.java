package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Damage;
import com.ecut.warehouse.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DamageDao {

    //入参是两个字符串的时候，在此加上@Param
    void insertGoodDamageRel(@Param("goodId") String goodId,@Param("damageId") String damageId, @Param("count") Integer count);

    void insertDamage(Damage damage);

    List<Damage> selectAllDamages(Damage damage);

    Damage queryById(String damageId);

    List<Goods> queryGoodsById(String damageId);

    void updateStatus(@Param("damageId") String damageId,@Param("status") Integer status);

    void deleteRelById(String damageId);

    void updateDamage(Damage damage);

    List<String> selectGoodsId(String damageId);

    Integer findCount(@Param("damageId")String damageId,@Param("goodId") String goodId);

}
