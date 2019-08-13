package com.jk.service;

import com.jk.dao.bookDao;

import com.jk.model.PermissionModel;
import com.jk.model.user;
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
        return null;
    }

    @Override
    public List<PermissionModel> gettree(Integer id) {
        return null;
    }
}
