package com.study.ssm.core;

import java.util.Date;

public class Role {
    
    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private String roleStatus;
    private Date regDate;
    private Integer regUserId;
    private String auditStatus;
    private Integer permitUserId;
    private Date permitDate;
    private String resv1;
    private String resv2;
    private String resv3;
    private String resv4;
    private String resv5;
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescription() {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    public String getRoleStatus() {
        return roleStatus;
    }
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public Integer getRegUserId() {
        return regUserId;
    }
    public void setRegUserId(Integer regUserId) {
        this.regUserId = regUserId;
    }
    public String getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    public Integer getPermitUserId() {
        return permitUserId;
    }
    public void setPermitUserId(Integer permitUserId) {
        this.permitUserId = permitUserId;
    }
    public Date getPermitDate() {
        return permitDate;
    }
    public void setPermitDate(Date permitDate) {
        this.permitDate = permitDate;
    }
    public String getResv1() {
        return resv1;
    }
    public void setResv1(String resv1) {
        this.resv1 = resv1;
    }
    public String getResv2() {
        return resv2;
    }
    public void setResv2(String resv2) {
        this.resv2 = resv2;
    }
    public String getResv3() {
        return resv3;
    }
    public void setResv3(String resv3) {
        this.resv3 = resv3;
    }
    public String getResv4() {
        return resv4;
    }
    public void setResv4(String resv4) {
        this.resv4 = resv4;
    }
    public String getResv5() {
        return resv5;
    }
    public void setResv5(String resv5) {
        this.resv5 = resv5;
    }
    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDescription=" + roleDescription
                + ", roleStatus=" + roleStatus + ", regDate=" + regDate + ", regUserId=" + regUserId + ", auditStatus="
                + auditStatus + ", permitUserId=" + permitUserId + ", permitDate=" + permitDate + ", resv1=" + resv1
                + ", resv2=" + resv2 + ", resv3=" + resv3 + ", resv4=" + resv4 + ", resv5=" + resv5 + "]";
    }
    
    
    
    
    
    
    
}
