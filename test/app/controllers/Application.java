package controllers;

import play.*;
import play.data.validation.Required;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends    Controller {


    public static void index() {
        render();
    }

    public  static void sayHello(String myName){render(myName);}

    public static  void login (String userid, String password) {

        Staff staff= Staff.find("byUserid",userid).first();
            if(islogin(userid,password)){
                String name=staff.name;
                render("../views/Application/success.html",staff);
            }
            else{

                String error="userId or Password Worng";
                render("../views/Application/index.html", error);
            }
    }
    public static boolean islogin(String userid, String password){
         long num=Staff.count("password=? and userid=?",password,userid);
         if(num>0)
             return true;
         else
             return false;
    }
    public static void modifyInfo(Long id, @Required String userid, @Required String name){
        Staff staff=Staff.findById(id);
        List <Excuse> excuseList=Excuse.findAll();
        staff.modify(userid,name);
        render(staff);
    }
    public static void modifyPassword(Long id, @Required String password,@Required String passwordConfirm){
        Staff staff=Staff.findById(id);
        if(password.equals(passwordConfirm)){
            staff.setPassword(password);
            render("../views/Application/success.html",staff);
        }
        else{
            String error="password not confirmed";
            render("../views/Application/success.html",error,staff);
        }
    }

}