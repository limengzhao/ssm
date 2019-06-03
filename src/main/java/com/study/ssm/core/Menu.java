package com.study.ssm.core;

import java.util.Date;
/**
 * �˵�ʵ����
 * @author limengzhao
 *
 */
public class Menu {
    
    private Integer menuid;
    private String menuName;
    private Integer parentMenuId;
    private String menuUrl;
    private char status;
    private String remark;//��ע
    private Integer crateMenuUser;
    private Date createTime;
    private Date updateTime;
    private Integer updateUser;
   
    
    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getMenuid() {
        return menuid;
    }
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
        this.status = status;
    }
    public Integer getCrateMenuUser() {
        return crateMenuUser;
    }
    public void setCrateMenuUser(Integer crateMenuUser) {
        this.crateMenuUser = crateMenuUser;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
    public Integer getParentMenuId() {
        return parentMenuId;
    }
    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
}
