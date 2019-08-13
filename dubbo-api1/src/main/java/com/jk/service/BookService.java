package com.jk.service;

import com.jk.model.PermissionModel;
import com.jk.model.RoleModel;
import com.jk.model.user;

import java.util.List;
import java.util.Map;

public interface BookService {

    Map query(Integer page, Integer rows);

    user login(user uu);

    List<PermissionModel> gettree(Integer id);

    List<RoleModel> getRoleByUserId(Integer userId);

  void updateUserRole(Integer[] roleIds, Integer uidTwo);

    List<PermissionModel> getPermissionByRId(Integer id);

    int updateRolePermiss(Integer[] mids, Integer roleid);

    Map queryje(Integer page, Integer rows);

    List<user> queryjj();

    void addBook(List<user> list);
}
