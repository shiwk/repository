package controllers;

import models.Excuse;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by s on 2015/12/19.
 */
public class Manager extends Controller{

    //查看所有待审批假条
    public static void checkExcuseToPass(){
        List<Excuse> excuses=Excuse.find("byState",2).fetch();
        renderJSON(excuses);
    }
    //批准
    public static void approval(Long id, String replyM) {
        Excuse excuse = Excuse.findById(id);
        excuse.replyM = replyM;
        excuse.state=3;
        excuse.replyTimeM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //拒绝
    public static void reject(Long id, String replyM){
        Excuse excuse = Excuse.findById(id);
        excuse.replyM = replyM;
        excuse.state+=1;
        excuse.state=excuse.state*(-1);
        excuse.replyTimeM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //查看审批过的假条
    public static void checkExcusePassed(){
        List<Excuse> excuses=Excuse.find("select e from Excuse e where e.state=3 or e.state=-3.").fetch();
        renderJSON(excuses);
    }
}
