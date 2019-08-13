package com.jk.mapper;

import com.jk.model.usermodel;

import java.util.List;

public interface Mmapper {


    List<usermodel> query();

    void addBook(List<usermodel> list);
}
