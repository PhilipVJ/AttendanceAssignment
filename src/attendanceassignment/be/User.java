/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philip
 */
public class User
{

    private String firstname;
    private String lastname;
    private String type;
    private final int id;
    private List<String> classes;

    public User(int id, String firstname, String lastname, String type)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        classes = new ArrayList<>();
    }

    public void addClass(String className)
    {

        classes.add(className);

    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getType()
    {
        return type;
    }

    public int getId()
    {
        return id;
    }

    public List<String> getAllClasses()
    {
        return classes;
    }

    public String getFirstClass()
    {
        return classes.get(0);
    }

}
