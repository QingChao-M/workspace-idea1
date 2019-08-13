package com.jk.service;

import com.jk.model.usermodel;

import java.util.List;

public interface sservice {
    List<usermodel> query();

    void addBook(List<usermodel> list);
}
