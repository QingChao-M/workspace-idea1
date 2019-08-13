package com.jk.service;

import com.jk.dao.bookDao;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "bookService")
public class BookServiceImpl implements BookService{

@Autowired
private bookDao bookdao;



    @Override
    public Map query(Integer page,  Integer rows) {
       int sum= bookdao.zong();
        System.out.println(page);
       Integer start=(page-1)*rows;
        List<user> list=bookdao.query(start,rows);
        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",sum);
        return map;
    }

    @Override
    public user login(user uu) {
        return bookdao.login(uu);
    }

    @Override
    public List<PermissionModel> gettree(Integer id) {
        return  bookdao.gettree(id);
    }

    @Override
    public List<RoleModel> getRoleByUserId(Integer userId) {
        // 先查 自己的
        List<Integer> list =   bookdao.getRoleByUserId(userId);

        // 再查所有的 角色
        List<RoleModel> listtwo =   bookdao.queryRole();


        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listtwo.size(); j++) {


                if(list.get(i) == listtwo.get(j).getId()){

                    listtwo.get(j).setChecked("true");

                }

            }

        }


        return listtwo;
    }

    /**
     * 编辑 用户 角色
     * @param roleIds
     * @param uidTwo
     * @return
     */
    @Override
    public void updateUserRole(Integer[] roleIds, Integer uidTwo) {
        //先 删除 该用户  原来 有的 角色
        bookdao.shanuj(uidTwo);
        //再新增 现在 要加的  角色

            for (int j = 0; j < roleIds.length; j++) {
                UserRoleModel urm = new UserRoleModel();
                urm.setJid(roleIds[j]);
                urm.setUid(uidTwo);
                bookdao.insert(urm);
        }
    }

    @Override
    public List<PermissionModel> getPermissionByRId(Integer id) {
        List<String> list =  bookdao.gettreeid(id);

        // 查询所有 的菜单

        List<PermissionModel>  listTwo = bookdao.gettreeall();

        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j < listTwo.size(); j++) {
                // 用  原来 选中的 菜单id  和所有的  菜单id  对比 ，对比成功 就选中
                if(list.get(i).equals(listTwo.get(j).getId().toString())){
                    listTwo.get(j).setChecked("true");
                }
            }
        }
        return listTwo;
    }




    @Override
    public int updateRolePermiss(Integer[] mids, Integer roleid) {
        //先 删除 原有的 关联
        int i  = bookdao.deljt(roleid);

        //再建立  新的 关联
        if(i >0){
            for (int j = 0; j < mids.length; j++) {
                jt rpm = new jt();
                rpm.setQid(mids[j]);
                rpm.setJid(roleid);
                i = bookdao.insertjt(rpm);
            }
        }
        return i;
    }

    @Override
    public Map queryje(Integer page, Integer rows) {
        int sum= bookdao.zong2();
        Integer start=(page-1) * rows;
        List<RoleModel> list=bookdao.queryje(start,rows);
        Map map=new HashMap();
        map.put("rows",list);
        map.put("total",sum);
        return map;
    }





//导入
    @Override
    public List<user> queryjj() {


        return bookdao.quer();
    }
    //导入
    @Override
    public void addBook(List<user> list) {
        bookdao.addBook( list);
    }

}
