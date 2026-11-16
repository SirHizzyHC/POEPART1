/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package POE;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */

public class LoginTest {
    @Test
    public void testValidUser() {
        Login l = new Login();
        assertTrue(l.checkUserName("kyl_1"));
    }
    @Test
    public void testInvalidUser() {
        Login l = new Login();
        assertFalse(l.checkUserName("kyle!!!!!!!"));
    }
    @Test
    public void testValidPass() {
        Login l = new Login();
        assertTrue(l.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    @Test
    public void testInvalidPass() {
        Login l = new Login();
        assertFalse(l.checkPasswordComplexity("password"));
    }
    @Test
    public void testValidPhone() {
        Login l = new Login();
        assertTrue(l.checkCellPhoneNumber("+27838968976"));
    }
    @Test
    public void testInvalidPhone() {
        Login l = new Login();
        assertFalse(l.checkCellPhoneNumber("08966553"));
    }
}