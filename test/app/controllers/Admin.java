package controllers;

import models.Excuse;
import models.Staff;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by s on 2015/12/20.
 */
public class Admin extends Controller {
    //添加员工
    public static void addStaff(String userId, String password, String name, int degree, int isMarried, int sex, @As("dd/MM/yyyy")Date birthDate, @As("dd/MM/yyyy")Date entryDate, int remainDays ,int department){
        Staff staff=new Staff(userId,password,name,degree,isMarried,sex,birthDate,entryDate,remainDays,department);
        staff.save();
        renderJSON(staff);
    }
    //删除员工
    public static void deleteStaff(Long id){
        Staff staff=Staff.findById(id);
        List<Excuse> excuses=Excuse.find("select e from Excuse e where e.userId=? order by e.startDate",staff.userId).fetch();
        for (Excuse excuse:excuses){
            excuse.delete();
        }
        staff.remove();
    }
    //修改员工信息
    public static void modifyStaff(Long id,String name, int degree, int isMarried, int sex, Date birthDate, Date entryDate, int remainDays ,int department){
        Staff staff=Staff.findById(id);
        render(staff.modify(name,degree,isMarried,sex,birthDate,entryDate,remainDays,department));
    }
    //根据部门查询员工
    public static void checkStaffbyDepartment(int department){
        List<Staff> staffs=Staff.find("byDepartment",department).fetch();
        renderJSON(staffs);
    }
    //根据入职时间查询员工
    public static void checkStaffbyEntryDate( @Required @As("dd/MM/yyyy") Date startDate,@Required @As("dd/MM/yyyy")Date endDate){
        List<Staff> staffs=Staff.find( "select s from  Staff s " +
                "where s.entryDate>=? and s.entryDate<=?",startDate,endDate).fetch();
        renderJSON(staffs);
    }
}
