package com.udbac.versionpublish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 渠道实体类
 * 
 * @author dundun.wang
 * @date 2019/09/20
 */
@Table(name = "branch")
@Entity
public class Branch {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;// 渠道名称
    @Column(name = "dcsid")
    private String dcsid;// 渠道名称简写
    @Column(name = "province")
    private String province;// 所属省份
    @Column(name = "createTiem")
    private String createTime;// 创建日期
    @Column(name = "updateTime")
    private String updateTime;// 更新日期

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDcsid() {
        return dcsid;
    }

    public void setDcsid(String dcsid) {
        this.dcsid = dcsid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
