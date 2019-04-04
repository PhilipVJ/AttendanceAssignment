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

    public Teacher(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public void addClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

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
