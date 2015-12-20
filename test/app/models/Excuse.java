package models;

import play.data.binding.As;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by s on 2015/12/18.
 */
@Entity
public class Excuse extends Model {


    public String userId;
    public int days;
    public Date startDate;
    public Date endDate;
    public int excuseType;
    public int state;

    @As("dd/MM/yyyy HH:MM£»ss")public Date replyTimeDM;
    @As("dd/MM/yyyy HH:MM£»ss")public Date replyTimeM;
    @As("dd/MM/yyyy HH:MM£»ss")public Date replyTimeVM;

    @Lob
    public String reason;
    public String replyDM;
    public String replyVM;
    public String replyM;


    public Excuse(String userId, int days, Date startDate, Date endDate, String reason,  int excuseType){

        this.userId=userId;
        this.days=days;
        this.endDate=endDate;
        this.excuseType=excuseType;
        this.reason=reason;

        this.startDate=startDate;
        this.state=state;
        this.days=days;
    }
    public Excuse modify(@Required int days,@Required @As("dd/MM/yyyy")Date startDate,@Required @As("dd/MM/yyyy") Date endDate, @Required String reason,@Required int excuseType){
        this.days=days;
        this.startDate=startDate;
        this.endDate=endDate;
        this.reason=reason;
        this.excuseType=excuseType;
        this.save();
        return this;
    }
}
