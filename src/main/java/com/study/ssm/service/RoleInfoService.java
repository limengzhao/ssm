package com.study.ssm.service;

import java.util.List;

import com.study.ssm.core.Role;

public interface RoleInfoService {
    
    List<Role> findRoleAll(Integer roleId,Integer limit,Integer pageNumber);
    Integer selectPageCount(Integer roleid,Integer limit,Integer pageNumber);
    Role findRoleById(Integer roleId);
    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRole(Integer roleId);
    
}
