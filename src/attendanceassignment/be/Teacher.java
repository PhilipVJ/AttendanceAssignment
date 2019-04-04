/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.util.ArrayList;

public class Teacher {

    private final String firstName;
    private final String lastName;
    private final int id;
    private ArrayList<String> classes;

    /**
     * Constructor for a teacher in Teacher class
     * @param firstName
     * @param lastName
     * @param id 
     */
    public Teacher(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    /**
     * adds a class in arraylist
     * @param classes 
     */
    public void addClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    /**
     * Get the first name of a teacher in the Teacher class
     * @return FirstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of a teacher int the Teacher class
     * @return LastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the id of a teacher in the Teacher class
     * @return GetId
     */
    public int getId() {
        return id;
    }

    /**
     * Gets all the class in a arraylist of the teachers 
     * @return Arraylist Of class Teacher
     */
    public ArrayList<String> getClassses() {
        return classes;
    }

    /**
     * Override the first and last name to a string of a teacher
     * @return firstname and lastname as string
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
