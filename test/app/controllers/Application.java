package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import play.*;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends    Controller {


    public static void index() {
        render();
    }
    static Gson gson=new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

    public  static void sayHello(String myName){render(myName);}

    public static  void login (String userid, String password) {

        Staff staff= Staff.find("byUserid",userid).first();

            if(islogin(userid,password)){
              String name=staff.name;
                renderJSON(staff);
           }
           else{

                String error="userId or Password Worng";
                renderJSON(null);
                //render("../views/Application/index.html", error);
           }
    }
    public static boolean islogin(String userid, String password){
         long num = Staff.count("password=? and userid=?",password,userid);
         if(num>0)
             return true;
         else
             return false;
    }
    //�鿴������Ϣ
    public static void checkInfo(Long id){
        Staff staff=Staff.findById(id);
        renderJSON(gson.toJson(staff));
    }

    //д�����
    public static void writeLips(long id,@Required int days,@Required @As("yyyy/MM/dd")Date startDate,@Required @As("yyyy/MM/dd") Date endDate, @Required String reason,@Required int excuseType){
        Staff staff=Staff.findById(id);
        Excuse excuse=new Excuse(staff.userId,days,startDate,endDate,reason,excuseType);
        excuse.save();
        renderJSON(gson.toJson(excuse));
    }
    //�鿴����
    public static void checkExcuse(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find("byUserid",staff.userId).fetch();
        renderJSON(gson.toJson(excuses));
    }
    //�鿴δͨ���������
    public static  void checkExcuseNoPass(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find("byUseridAndState",staff.userId,0).fetch();
        renderJSON(gson.toJson(excuses));
    }
    //�޸ļ���
    public static void modifyExcuse(Long id,@Required int days,@Required @As("yyyy/MM/dd")Date startDate,@Required @As("yyyy/MM/dd") Date endDate, @Required String reason,@Required int excuseType){
        Excuse excuse=Excuse.findById(id);
        renderJSON(gson.toJson(excuse.modify(days, startDate, endDate, reason, excuseType)));
    }
    //ɾ�������
    public static void deleteExcuse(Long id){
        Excuse excuse=Excuse.findById(id);
        excuse.delete();
    }
    //�鿴ͬ���ŵ������˵������
    public static void checkExcuseInDepartment(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ?",staff.department).fetch();
        renderJSON(gson.toJson(excuses));
    }
    //�鿴ͬ����Ա���б�
    public static void  staffInDepartment(Long id){
        Staff staff=Staff.findById(id);
        List<Staff> staffs=Staff.find("byDepartment",staff.department).fetch();
        renderJSON(gson.toJson(staffs));
    }
//    public static void modifyInfo(long id, @Required String userid, @Required String name){
//        Staff staff=Staff.findById(id);
//       // List <Excuse> excuseList=Excuse.findAll();
//       // staff.modify(userid,name);
//        render(staff);
//    }
    //�޸�����
    public static void modifyPassword(Long id, @Required String password,@Required String passwordConfirm){
            Staff staff=Staff.findById(id);
        if(password.equals(passwordConfirm)){
            staff.setPassword(password);
            renderJSON(gson.toJson(staff));
        }
        else{

        }
    }
    //��ѯ������ʷ�����
    public static void checkExcusebyPerson(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find("select e from Excuse e where e.userId=? order by e.startDate",staff.userId).fetch();
        renderJSON(gson.toJson(excuses));
    }
}