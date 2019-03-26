/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.User;
import attendanceassignment.be.Student;
import attendanceassignment.be.Teacher;
import attendanceassignment.dal.DbConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nikla
 */
public class UserDB
{

    DbConnection db;

    public UserDB() throws IOException
    {
        db = new DbConnection();
    }

    public User userLogin(String username, String password) throws SQLException
    {

        User user = null;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        ResultSet rs = null;
        Connection con = db.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, username);
        pst.setString(2, password);

        rs = pst.executeQuery();

        if (rs.next())
        {
            int id = rs.getInt("personID");
            user = getUser(id);
        }
        return user;
    }

    private User getUser(int id) throws SQLServerException, SQLException
    {

        User user = null;
        String sql = "SELECT * FROM Person WHERE personID = ?";
        ResultSet rs = null;
        Connection con = db.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, id);

        rs = pst.executeQuery();
        if (rs.next())
        {
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String type = rs.getString("pType");
            user = new User(id, firstName, lastName, type);
            ArrayList<String> classes = getClasses(id);
            for (String schoolClass : classes)
            {
                user.addClass(schoolClass);
            }
        }

        return user;

    }

    public  ArrayList<String> getClasses(int id) throws SQLServerException, SQLException
    {
        ArrayList<String> allClasses = new ArrayList<>();
        String sql = "SELECT * FROM PersonClass INNER JOIN Class ON PersonClass.classID = Class.classID WHERE personID=(?);";
        ResultSet rs = null;
        Connection con = db.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        while (rs.next())
        {
            String className = rs.getString("classname");
            allClasses.add(className);

        }
        return allClasses;

    }

}
