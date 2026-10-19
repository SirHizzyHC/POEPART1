/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart1;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Student
 */
public class RegistrationNGTest {

    public RegistrationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of checkUserName method, of class Registration.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "_";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckUserName_Invalid() {
        System.out.println("checkUserName");
        String username = "0";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
    }

    /**
     * Test of checkPasswordComplexity method, of class Registration.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "#082961Huli";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }

    /**
     * TODO review the generated test code and remove the default call to fail.
     */
    @Test
    public void testCheckPasswordComplexity_Invalid() {
        System.out.println("checkPasswordComplexity");
        String password = "#082961";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Registration.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellphone = "+27753252546";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        System.out.println("checkCellPhoneNumber");
        String cellphone = "0713252546";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }

    /**
     * Test of registerUser method, of class Registration.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String firstName = "Hulisani";
        String lastName = "Nndwambi";
        String username = "_Huli";
        String password = "#082961Huli";
        String cellphone = "+27713252546";
        Registration instance = new Registration();
        String expResult = "Username successfully captured. Password successfully captured. Cellphone number successfully added. ";
        String result = instance.registerUser(firstName, lastName, username, password, cellphone);
        assertEquals(result, expResult);
    }

    /**
     * Test of registerStatus method, of class Registration.
     */
    @Test
    public void testRegisterStatus() {
        System.out.println("registerStatus");
        String username = "";
        String password = "";
        String cellphone = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }

}