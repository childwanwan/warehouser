package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yanhq@si-tech.com.cn
 * @Description: xx相关
 * @Copyright @SI-TECH 2019. All rights reserved
 * @date 2019-xx-xx
 * @version: V1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_instore")
public class Instore implements Serializable {

    private static final long serialVersionUID = -4466408732732571294L;

    private String id;
    //入库人id
    private String provideId;
    //入库日期
    private Date instoreTime;
    //数量合计
    private Integer totalNum;
    //状态
    private Integer status;
    //接收人id
    private String reserverId;
}
