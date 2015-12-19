package controllers;

import models.Excuse;
import models.Staff;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by s on 2015/12/19.
 */
//部门经理的功能
public class Department extends Controller {



    //批准
    public static void approval(Long id, String replyDM) {
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state+=1;
        excuse.replyTimeDM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //否决
    public static void reject(Long id, String replyDM){
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state+=1;
        excuse.state=excuse.state*(-1);
        excuse.save();
        renderJSON(excuse);
    }
    //查看部门所有待审批假条
    public static void checkExcuseToPass(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state=0",staff.department).fetch();
        renderJSON(excuses);
    }
    //查看已审批过的假条
    public static void checkExcusePassed(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state<>0",staff.department).fetch();
        renderJSON(excuses);
    }
    //按日期查询假条
    public static void checkExcusebyDate(Long id, @Required @As("dd/MM/yyyy") Date startDate, @Required @As("dd/MM/yyyy")Date endDate){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.startDate>=? and e.startDate<=?",staff.department,startDate,endDate).fetch();
        renderJSON(excuses);
    }

    //查看部门内所有审批过的假条
    //批准并上报

}