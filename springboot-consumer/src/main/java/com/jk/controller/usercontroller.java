package com.jk.controller;

import com.jk.model.PermissionModel;
import com.jk.model.RoleModel;
import com.jk.model.user;
import com.jk.service.BookService;
import com.jk.util.ExportExcel;
import com.jk.util.TreeNoteUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class usercontroller {
    @Autowired
    private BookService bookservice;
    @Autowired
    private  RedisTemplate  redisTemplate;


    @RequestMapping("login")
    @ResponseBody
    public  String login(user uu, HttpServletRequest request){
        user user2=bookservice.login(uu);
        if(user2==null){
            return  "null";
        }
      else if(!user2.getUserpass().equals(uu.getUserpass())){
             return  "passerror";
         }

        request.getSession().setAttribute("user",user2);
         return "success";
    }




    @RequestMapping("query")
    @ResponseBody
    public Map queryList(Integer page,Integer rows){

        return  bookservice.query(page,rows);
    }
    @RequestMapping("s2")
    public String chaa(){

        return "userquery";
    }
    @RequestMapping("s")
    public String cha(){

        return "index";
    }


    @RequestMapping("s1")
    public String treeshow(){

        return "show";
    }

    @RequestMapping("c3")
    public String gg(){

        return "jtb";
    }

    @RequestMapping("c1")
    public String hg(){

        return "poi";
    }
    //
    @RequestMapping("queryRole")
    @ResponseBody
    public Map queryl(Integer page,Integer rows){

        return  bookservice.queryje(page,rows);
    }

//树
  @RequestMapping("getTreeAll")
  @ResponseBody

public List<PermissionModel> gettree(HttpServletRequest request){

        user uu= (user) request.getSession().getAttribute("user");
      List<PermissionModel> list=new ArrayList<PermissionModel>();

        String key="key";
    if(!redisTemplate.hasKey("key")){
        System.out.println("缓存");
        list= (List<PermissionModel>) redisTemplate.opsForValue().get(key);
    }
      list= bookservice.gettree(uu.getId());
      list= TreeNoteUtil.getFatherNode(list);
    redisTemplate.opsForValue().set("key",list);
      redisTemplate.expire(key,15, TimeUnit.MINUTES)   ;

        return list;
}


    @RequestMapping("getRoleByUserId")
    public  @ResponseBody List<RoleModel>  getRoleByUserId(Integer userId){
        List<RoleModel> list = bookservice.getRoleByUserId(userId);
        System.out.println(list);
        return list;
    }
    @RequestMapping("updateUserRole")
    public  @ResponseBody void  updateUserRole(Integer[]  roleIds, Integer uidTwo){
          bookservice.updateUserRole(roleIds,uidTwo);
    }

//getMenu()
@RequestMapping("getMenu")
public  @ResponseBody List<PermissionModel>  getPermissionByRId(Integer id){

    List<PermissionModel> list = bookservice.getPermissionByRId(id);

    //自己调用自己（递归）
    list = TreeNoteUtil.getFatherNode(list);

    return list;


}

    @RequestMapping("updateRolePermiss")
    public  @ResponseBody String updateRolePermiss(Integer[] mids,Integer roleid){

        int i = bookservice.updateRolePermiss(mids,roleid);
        if(i<1){
            return "0";
        }
        return "1";
    }



    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, String a,Integer page,Integer rows){
        //导出的excel的标题

        String title = "1902B用户管理";
        //导出excel的列名
        String[] rowName = {"编号","名称","密码"};
        //导出的excel数据5
        List<Object[]>  dataList = new ArrayList<Object[]>();
        //查询的数据库的书籍信息
       List<user>list=  bookservice.queryjj();
        //循环书籍信息
        for(user user:list){
            Object[] obj =new Object[rowName.length];
            obj[0]=user.getId();
            obj[1]=user.getUsername();
            obj[2]=user.getUserpass();

            // SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            // String format = sdf.format(book.getBooktime());
            //obj[3]=format;
            // obj[4]=book.getBookurl();

            dataList.add(obj);
        }
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //获得上传文件上传的类型
        String contentType = file.getContentType();
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //获得文件的后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件的新的路径
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";
        //创建list集合接收excel中读取的数据
        List<user> list =new ArrayList<user>();
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件忽的WorkBook
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //通过workbook对象获得sheet页 有可能不止一个sheet
            for(int i=0 ;i<workbook.getNumberOfSheets();i++){
                //获得里面的每一个sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //通过sheet对象获得每一行
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建一个book对象接收excel的数据
                    user book=new user();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据
                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        book.setUsername(this.getCellValue(row.getCell(1)));
                    }
                    if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        book.setUserpass((this.getCellValue(row.getCell(2))));
                    }




                    list.add(book);
                }
            }
            bookservice.addBook(list);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/query";


    }

    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }









}
