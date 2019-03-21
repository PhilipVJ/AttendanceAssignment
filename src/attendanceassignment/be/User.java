/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

/**
 *
 * @author Philip
 */
public class User {

    private String firstname;
    private String lastname;
    private String type;
    private final int id;

    public User(int id, String firstname, String lastname, String type) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
    
    
    
    

}
