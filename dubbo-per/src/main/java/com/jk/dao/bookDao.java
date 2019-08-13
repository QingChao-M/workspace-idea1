package com.jk.dao;

import com.jk.model.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface bookDao {


    int zong();

    List<user> query(@Param("start") Integer start, @Param("rows") Integer rows);
}
