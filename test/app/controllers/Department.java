package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import models.Excuse;
import models.Staff;



import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by s on 2015/12/19.
 */
//部门经理的功能
public class Department extends Controller {

    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy/MM/dd HH:mm:ss")
            .create();
    //批准
    public static void approval(Long id, String replyDM) {
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state+=1;
        Date date = new Date();
        excuse.replyTimeDM=date;
        excuse.save();
        renderJSON(gson.toJson(excuse));

//        JSONObject jsonObject = JSONObject.fromObject(excuse);
//        jsonObject.element("replyTimeDM",FormatDateTime.toShortTimeString(excuse.replyTimeDM));
//        System.out.println(FormatDateTime.toShortTimeString(excuse.replyTimeDM));
//        try {
//            FormatDateTime.toshortDate(FormatDateTime.toShortTimeString(excuse.replyTimeDM));
//        } catch (ParseException e) {
//            //e.printStackTrace();
//        }
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  //      excuse.replyTimeDM = (Date) Timestamp.valueOf(sf.format(date));

    }
    //否决
    public static void reject(Long id, String replyDM){
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state += 1;
        excuse.state=excuse.state*(-1);
        excuse.replyTimeM=new Date();
        excuse.save();

        renderJSON(gson.toJson(excuse));
    }
    //查看部门所有待审批假条
    public static void checkExcuseToPass(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state=0",staff.department).fetch();
        renderJSON(gson.toJson(excuses));
    }
    //查看已审批过的假条
    public static void checkExcusePassed(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state<>0",staff.department).fetch();
        renderJSON(gson.toJson(excuses));
    }
    //按日期查询假条
    public static void checkExcusebyDate(Long id, @Required @As("yyyy/MM/dd") Date startDate, @Required @As("yyyy/MM/dd")Date endDate){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.startDate>=? and e.startDate<=?",staff.department,startDate,endDate).fetch();
        renderJSON(gson.toJson(excuses));
    }

    //查看部门内所有审批过的假条
    //批准并上报

}