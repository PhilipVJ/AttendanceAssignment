/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class User
{

    private final String firstName;
    private final String lastName;
    private final String type;
    private final int id;
    private final ArrayList<String> classes;

    public User(int id, String firstname, String lastname, String type)
    {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.type = type;
        classes = new ArrayList<>();
    }

    public void addClass(String className)
    {

        classes.add(className);
    }

    public String getFirstname()
    {
        return firstName;
    }

    public String getLastname()
    {
        return lastName;
    }

    public String getType()
    {
        return type;
    }

    public int getId()
    {
        return id;
    }

    // Returns the list of all classes. If the user is a student - the list size is only 1.
    public ArrayList<String> getAllClasses()
    {
        return classes;
    }
    
    public String toString()
    {
        return firstName + " " +lastName;
    }
    
    



}
