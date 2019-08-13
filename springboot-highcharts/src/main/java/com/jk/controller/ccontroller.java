package com.jk.controller;

import com.jk.service.sservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ccontroller {
    @Autowired
    private sservice sservice;

    @RequestMapping("s")
    public String toshow() {
        return "show";
    }
    @RequestMapping("s1")
    public String toshow2() {
        return "show2";
    }




    @RequestMapping("query1")
    @ResponseBody
    public List<Map<String, Object>> querybook() {
        List<Map<String, Object>> list = sservice.querybook();
        List<Map<String, Object>> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            Map<String, Object> map = new HashMap<>();

            map.put("y", Integer.parseInt(list.get(i).get("总个数").toString()));
            map.put("name",list.get(i).get("时间"));
            list2.add(map) ;
        }

        return list2;

    }

         @RequestMapping("queryBook2")
         @ResponseBody
              public  List<Map<String, Object>> querybook2() {
                         List<Map<String, Object>> list = sservice.querybook2();
                 List<Map<String, Object>> list2 = new ArrayList<>();


                         for (Map<String, Object>map1:list){
                             Map<String, Object> map = new HashMap<>();
                    String obj= map1.get("时间").toString();
                   int[] arr=new int[4];

                          if(obj.equals(obj)){
                              map.put("name",obj);
                       //int [] aa =new int[]{200,600,900};
                              arr[0]= Integer.parseInt(map1.get("总个数").toString());
                              map.put("y",arr[0]);
                          }
                             if(obj.equals(obj)){
                                 map.put("name",obj);
                                 arr[1]= Integer.parseInt(map1.get("总个数").toString());
                                 map.put("y",arr[1]);
                             }
                             if(obj.equals(obj)){
                                 map.put("name",obj);
                                 arr[2]= Integer.parseInt(map1.get("总个数").toString());
                                 map.put("y",arr[2]);
                             }
                             if(obj.equals(obj)){
                                 map.put("name",obj);
                                 arr[3]= Integer.parseInt(map1.get("总个数").toString());
                                 map.put("y",arr[3]);
                             }
                             list2.add(map);

                         }




                         return list2;

                     }
}