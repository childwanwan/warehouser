package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Instore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface InstoreDao {
    void deleteById(String instoreId);

    Instore selectById(String instoreId);

    List<Instore> selectAll(Instore instore);

    void insertInstore(Instore instore);

    void insertRel(Map<String, Object> map);
}
