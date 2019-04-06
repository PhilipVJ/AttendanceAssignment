/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philip
 */
public class TeacherTest {

    public TeacherTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addClasses method, of class Teacher.
     */
    @Test
    public void testAddClasses() {
        Teacher t = new Teacher("Johan", "Simonsen", 1);
        ArrayList<String> classes = new ArrayList<>();
        classes.add("CS2018");
        classes.add("CS2019");
        classes.add("CS2020");
        t.addClasses(classes);
        assertEquals(classes, t.getClassses());
    }

    /**
     * Test of getFirstName method, of class Teacher.
     */
    @Test
    public void testGetFirstName() {
        Teacher t = new Teacher("John", "Simonsen", 9);
        assertEquals("John", t.getFirstName());
    }

    /**
     * Test of getLastName method, of class Teacher.
     */
    @Test
    public void testGetLastName() {
        Teacher t = new Teacher("John", "Simonsen", 9);
        assertEquals("Simonsen", t.getLastName());
    }

    /**
     * Test of getId method, of class Teacher.
     */
    @Test
    public void testGetId() {
        Teacher t = new Teacher("John", "Simonsen", 10);
        assertEquals(10, t.getId());
    }

    /**
     * Test of getClassses method, of class Teacher.
     */
    @Test
    public void testGetClassses() {
        Teacher t = new Teacher("Johan", "Simonsen", 1);
        ArrayList<String> classes = new ArrayList<>();
        classes.add("CS2018");
        classes.add("CS2019");
        classes.add("CS2020");
        t.addClasses(classes);
        assertEquals(classes, t.getClassses());
    }

    /**
     * Test of toString method, of class Teacher.
     */
    @Test
    public void testToString() {
        Teacher t = new Teacher("Michael", "Simonsen", 18);
        assertEquals("Michael Simonsen", t.toString());

    }

}
