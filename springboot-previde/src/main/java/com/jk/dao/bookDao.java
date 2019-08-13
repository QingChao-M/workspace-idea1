package com.jk.dao;

import com.jk.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface bookDao {


    int zong();

    List<user> query(@Param("start") Integer start, @Param("rows") Integer rows);

    user login(user uu);

    List<PermissionModel> gettree(Integer id);




    List<Integer> getRoleByUserId(Integer userId);

    List<RoleModel> queryRole();

    void shanuj(Integer uidTwo);



    void insert(UserRoleModel urm);




    List<String> gettreeid(Integer id);

    List<PermissionModel> gettreeall();

    int deljt(Integer roleid);

    int insertjt(jt rpm);



    int zong2();

    List<RoleModel> queryje(@Param("start") Integer start,@Param("rows") Integer rows);

    List<user> quer();

    void addBook(List<user> list);
}
