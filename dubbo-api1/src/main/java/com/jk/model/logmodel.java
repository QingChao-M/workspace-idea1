package com.jk.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class logmodel {
  private String id;
   private String name;
   private String logip;
   private String createtime;
   private String nr;
   private String Requerpath;
   private String userid;
   @DateTimeFormat(pattern = "yyyy-MM-dd") // 处理从	前端到后端的时间
   @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")// 处理从	后端到前端的时间
    private Date sdate;
   @DateTimeFormat(pattern = "yyyy-MM-dd") // 处理从	前端到后端的时间
   @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")// 处理从	后端到前端的时间
    private Date edate;
   
public Date getSdate() {
	return sdate;
}
public void setSdate(Date sdate) {
	this.sdate = sdate;
}
public Date getEdate() {
	return edate;
}
public void setEdate(Date edate) {
	this.edate = edate;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLogip() {
	return logip;
}
public void setLogip(String logip) {
	this.logip = logip;
}

public String getNr() {
	return nr;
}
public void setNr(String nr) {
	this.nr = nr;
}
public String getRequerpath() {
	return Requerpath;
}
public void setRequerpath(String requerpath) {
	Requerpath = requerpath;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
}
   

   
}
