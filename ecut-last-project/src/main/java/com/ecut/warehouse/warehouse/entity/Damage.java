package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_damage")
public class Damage implements Serializable {
    private static final long serialVersionUID = 5791556212996613922L;

    private String id;//主键

    private String deep;//报损程度

    private String damageName;//报损名称

    private String damageType;//报损类型

    private Date createTime;//创建时间

    private Date approvalTime;//审批日期

    private String comment;//备注

    private Integer status;//报损状态（进度）

    private String createPersonId;//提交人

    private String checkPersonId;//审批人
}
