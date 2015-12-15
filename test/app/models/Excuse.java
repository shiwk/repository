package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by s on 2015/12/12.
 */
@Entity
public class Excuse extends Model {

    public int userId;
    public int days;
    public Date startDate;
    public Date endDate;

    @Lob
    public String reason;
    public String reply;

    public int excuseType;
    public int state;

    public Excuse(int userId, int days, Date startDate, Date endDate, String reason, String reply, int excuseType, int state){

        this.days=days;
        this.endDate=endDate;
        this.excuseType=excuseType;
        this.reason=reason;
        this.reply=reply;
        this.startDate=startDate;
        this.state=state;
        this.days=days;
    }
}
