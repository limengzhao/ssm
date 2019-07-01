package com.study.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.ssm.core.Role;

public interface RoleInfoDao {
    
    List<Role> findRoleAll(@Param(value="roleId")Integer roleId,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
    Integer selectPageCount(@Param(value="roleId")Integer roleId,@Param(value="limit")Integer limit,@Param(value="pageNumber")Integer pageNumber);
    Role findRoleById(@Param(value="roleId")Integer roleId);
    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRole(@Param(value="roleId")Integer roleId); 
    
}
