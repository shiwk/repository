package controllers;

import models.Excuse;
import models.Staff;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by s on 2015/12/19.
 */
public class ViceManager extends Controller{

    //查看所有待审批假条
    public static void checkExcuseToPass(){
        List<Excuse>excuses=Excuse.find("byState",1).fetch();
        renderJSON(excuses);
    }
    //批准
    public static void approval(Long id, String replyVM) {
        Excuse excuse = Excuse.findById(id);
        excuse.replyVM = replyVM;
        if (excuse.days<=3){
            excuse.state=3;
        }
        else
            excuse.state+=1;
        excuse.replyTimeVM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //否决
    public static void reject(Long id, String replyVM){
        Excuse excuse = Excuse.findById(id);
        excuse.replyVM = replyVM;
        excuse.state+=1;
        excuse.state=excuse.state*(-1);
        excuse.replyTimeVM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //查看审批过的假条
    public static void checkExcusePassed(){
        List<Excuse> excuses=Excuse.find("select e from Excuse e where e.state=2 or e.state=3 or e.state=-2.").fetch();
        renderJSON(excuses);
    }
    //按照部门查询假条
    public static void checkExcusebyDepartment(int department){
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state=0",department).fetch();
        renderJSON(excuses);
    }



}
