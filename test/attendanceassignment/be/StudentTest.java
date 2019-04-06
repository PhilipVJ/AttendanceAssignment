/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.be;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philip
 */
public class StudentTest {
    
    public StudentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getFirstName method, of class Student.
     */
    @Test
    public void testGetFirstName() {
        Student s = new Student("Jens", "Hansen", "CS2018", 13, 1);
        assertEquals("Jens", s.getFirstName());
    }

    /**
     * Test of getLastName method, of class Student.
     */
    @Test
    public void testGetLastName() {
        Student s = new Student("Jens", "Hansen", "CS2018", 13, 1);
        assertEquals("Hansen", s.getLastName());
    }

    /**
     * Test of setLastName method, of class Student.
     */
    @Test
    public void testSetLastName() {
        Student s = new Student("Jens", "Hansen", "CS2018", 13, 1);
        s.setLastName("Johansen");
        assertEquals("Johansen", s.getLastName());
    }

    /**
     * Test of getClassName method, of class Student.
     */
    @Test
    public void testGetClassName() {
        Student s = new Student ("Kaj","Michaelsen","CS2019",16,2);
        assertEquals("CS2019", s.getClassName());
    }

    /**
     * Test of getAbsence method, of class Student.
     */
    @Test
    public void testGetAbsence() {
    Student s = new Student ("Kaj","Michaelsen","CS2019",16,2);
        assertEquals("16,0", s.getAbsence());
    }

    /**
     * Test of getAbsenceDouble method, of class Student.
     */
    @Test
    public void testGetAbsenceDouble() {
            Student s = new Student ("Kaj","Michaelsen","CS2019",16,2);
        assertEquals(16, s.getAbsenceDouble(),1E-3);
    }

    /**
     * Test of getId method, of class Student.
     */
    @Test
    public void testGetId() {
        Student s = new Student ("Mogens","Johansen","CS2019",15,5);
        assertEquals(5, s.getId(),1E-3);
    }
    
}
