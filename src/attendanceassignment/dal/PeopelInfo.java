/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import attendanceassignment.dal.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author nikla
 */
public class PeopelInfo {

    ResultSet rs = null;
    
    DbConnection db;
    
    public PeopelInfo() throws IOException
    {
        db = new DbConnection();
    }
    
    public boolean loginConnection(String username, String password, int type) throws SQLException 
    {
         try{
        String sql = "SELECT * FROM Login WHERE Name = ? AND Password = ? AND TypeTS = ?";
        
        Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
                  
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, type);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                System.out.println("User: " + username + " Password: " + password);
                return true;
            
            } else {
                JOptionPane.showMessageDialog(null, "username and password dont match");
            }
            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Connection to database is lost!");
            }
            return false;
        }
}
 