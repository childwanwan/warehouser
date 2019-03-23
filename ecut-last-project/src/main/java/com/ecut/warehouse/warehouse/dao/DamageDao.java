package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Damage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DamageDao {

    void insertGoodDamageRel(@Param("goodId") String goodId,@Param("damageId") String damageId);

    void insertDamage(Damage damage);

    List<Damage> selectAllDamages(Damage damage);

}
