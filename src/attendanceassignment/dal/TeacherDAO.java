/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.be.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class TeacherDAO {

    DbConnection dbc;

    public TeacherDAO() throws IOException {
        this.dbc = new DbConnection();
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLServerException, SQLException {

        ArrayList<Teacher> allTeachers = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE pType='Teacher'";
        try (Connection con = dbc.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("firstname");

                String lastname = rs.getString("lastname");

                int id = rs.getInt("personID");

                Teacher toAdd = new Teacher(firstName, lastname, id);
                allTeachers.add(toAdd);

            }
            return allTeachers;
        }
    }
}