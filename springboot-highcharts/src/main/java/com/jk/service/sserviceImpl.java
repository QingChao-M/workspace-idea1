package com.jk.service;

import com.jk.dao.ddao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class sserviceImpl implements sservice {
      @Autowired
    private ddao ddao;


    @Override
    public List<Map<String, Object>> querybook() {

        return ddao.querybook();
    }

    @Override
    public List<Map<String, Object>> querybook2() {
        return ddao.querybook2();
    }
}
