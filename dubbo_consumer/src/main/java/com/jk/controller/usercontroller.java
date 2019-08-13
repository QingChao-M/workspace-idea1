package com.jk.controller;

import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("book")
public class usercontroller {
    @Autowired
    private BookService bookservice;







    @RequestMapping("query")
    @ResponseBody
    public Map queryList(Integer page,Integer rows){

        return  bookservice.query(page,rows);
    }
    @RequestMapping("s")
    public String cha(){

        return "show";
    }
}
