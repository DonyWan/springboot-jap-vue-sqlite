package com.udbac.versionpublish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 版本实体类
 * 
 * @author dundun.wang
 * @date 2019/09/20
 */
@Entity
@Table(name = "version")
public class Version {
    @Id
    private String id;
    @Column(name = "version_number")
    private String versionNumber;// 版本号
    @Column(name = "version_branch")
    private String versionBranch;// 版本分类
    @Column(name = "version_date")
    private String versionDate;// 版本日期
    @Column(name = "province")
    private String province;// 所属省份
    @Column(name = "notes")
    private String notes;// 更新说明
    @Column(name = "user")
    private String user;// 操作用户
//    @Column(name = "branch_id")
//    private String branchId;// 所属渠道
    @Column(name = "create_time")
    private String createTime;// 创建时间
    @Column(name = "update_time")
    private String updateTime;// 更新时间
    @Column(name = "status")
    private String status;// 状态
    @Column(name = "uploadDir")
    private String uploadDir;

    @OneToOne()
    @NotFound(action = NotFoundAction.IGNORE)
    private Branch branch;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getVersionBranch() {
        return versionBranch;
    }

    public void setVersionBranch(String versionBranch) {
        this.versionBranch = versionBranch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
