package com.study.ssm.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.ssm.core.Role;
import com.study.ssm.dao.RoleInfoDao;
import com.study.ssm.service.RoleInfoService;

@Service
public class RoleMenuInfoServiceImpl implements RoleInfoService {

    @Autowired
    RoleInfoDao roleInfoDao;
    
    @Override
    public List<Role> findRoleAll(Integer roleId,Integer limit,Integer pageNumber) {
        // TODO Auto-generated method stub
        return roleInfoDao.findRoleAll ( roleId, limit, pageNumber);
    }

    @Override
    public Integer selectPageCount(Integer roleId, Integer limit, Integer pageNumber) {
        // TODO Auto-generated method stub
        return roleInfoDao.selectPageCount (roleId , limit , pageNumber);
    }

    @Override
    public void saveRole(Role role) {
        roleInfoDao.saveRole (role);
    }

    @Override
    public void updateRole(Role role) {
        roleInfoDao.updateRole (role);
    }

    @Override
    public Role findRoleById(Integer roleId) {
        // TODO Auto-generated method stub
        return roleInfoDao.findRoleById (roleId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleInfoDao.deleteRole (roleId);
    }
    
}
