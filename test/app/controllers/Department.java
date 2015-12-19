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
//���ž���Ĺ���
public class Department extends Controller {



    //��׼
    public static void approval(Long id, String replyDM) {
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state+=1;
        excuse.replyTimeDM=new Date();
        excuse.save();
        renderJSON(excuse);
    }
    //���
    public static void reject(Long id, String replyDM){
        Excuse excuse = Excuse.findById(id);
        excuse.replyDM = replyDM;
        excuse.state+=1;
        excuse.state=excuse.state*(-1);
        excuse.save();
        renderJSON(excuse);
    }
    //�鿴�������д���������
    public static void checkExcuseToPass(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state=0",staff.department).fetch();
        renderJSON(excuses);
    }
    //�鿴���������ļ���
    public static void checkExcusePassed(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.state<>0",staff.department).fetch();
        renderJSON(excuses);
    }
    //�����ڲ�ѯ����
    public static void checkExcusebyDate(Long id, @Required @As("dd/MM/yyyy") Date startDate, @Required @As("dd/MM/yyyy")Date endDate){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find( "select e from Excuse e, Staff s " +
                "where s.userId = e.userId and s.department= ? and e.startDate>=? and e.startDate<=?",staff.department,startDate,endDate).fetch();
        renderJSON(excuses);
    }

    //�鿴�����������������ļ���
    //��׼���ϱ�

}