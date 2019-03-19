/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

/**
 *
 * @author Anders
 * 
 */

/**
 * class not yet in use
 */
public class Teacher {
    String username;
    String password;
    
    public String getName(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String setPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
