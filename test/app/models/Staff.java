package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Staff extends Model {

    public String userId;
    public String password;
    public String name;
    public int degree;
    public int remainDays;
    public int isMarried;
    public int sex;
    public Date birthDate;
    public Date entrydate;
   // public Date postedAt;

   // @Lob
    // public String content;



    public Staff(String userId, String password, String name, int degree, int isMarried, int sex, Date birthDate, Date entrydate, int remainDays ){

        this.userId=userId;
        this.birthDate=birthDate;
        this.degree=degree;
        this.entrydate=entrydate;
        this.isMarried=isMarried;
        this.name=name;
        this.password=password;
        this.remainDays=remainDays;
        this.sex=sex;
    }
    public Staff modify( String userid,  String name){
        this.userId=userid;
        this.name=name;
        this.save();
        return this;
    }
    public Staff setPassword( String password){
        this.password=password;
        this.save();
        return this;
    }

}