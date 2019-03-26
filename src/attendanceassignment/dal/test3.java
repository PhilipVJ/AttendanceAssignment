/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.Teacher;
import attendanceassignment.be.User;
import attendanceassignment.gui.AttModel.Utility;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anders
 */
public class test3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, SQLException {
UserDB uDb = new UserDB();
User user = uDb.userLogin("mog88", "mog88");
        System.out.println(""+user.getAllClasses().size());


        

    }
    
}
