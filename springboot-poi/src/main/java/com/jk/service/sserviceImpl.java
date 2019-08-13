package com.jk.service;

import com.jk.mapper.Mmapper;
import com.jk.model.usermodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class sserviceImpl implements  sservice {
    @Autowired
    private Mmapper mmapper;

    @Override
    public List<usermodel> query() {
        List<usermodel> list=mmapper.query();
        return list;
    }

    @Override
    public void addBook(List<usermodel> list) {
        mmapper.addBook( list);
    }
}
