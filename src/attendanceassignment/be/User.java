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

    /**
     * Constructor for the user class
     * @param id
     * @param firstname
     * @param lastname
     * @param type 
     */
    public User(int id, String firstname, String lastname, String type)
    {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.type = type;
        classes = new ArrayList<>();
    }

    /**
     * add a classname to a arraylist in the Userclass
     * @param className 
     */
    public void addClass(String className)
    {
        classes.add(className);
    }

    /**
     * Get the first name of a user in Userclass
     * @return FirstName
     */
    public String getFirstname()
    {
        return firstName;
    }

    /**
     * Get the last name of a user in userclass
     * @return LastName
     */
    public String getLastname()
    {
        return lastName;
    }

    /**
     * Getting the type of a user , "Teacher or a student" in UserClass
     * @return TheType
     */
    public String getType()
    {
        return type;
    }

    /**
     * Get the id of a user in User class
     * @return GetId
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the list of all classes. If the user is a student - the list size is only 1.
     * @return ArrayList of classes in Users
     */
    public ArrayList<String> getAllClasses()
    {
        return classes;
    }
    
    /**
     * Get a string with the first name and last name of a user.
     * @return FirstName and lastName
     */
    public String toString()
    {
        return firstName + " " +lastName;
    }
    
    



}
