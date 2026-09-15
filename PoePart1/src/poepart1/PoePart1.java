/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class PoePart1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login register = new Login();
        Scanner scan = new Scanner(System.in);

        System.out.println("\n====== Register ======");

        // First & Last Name inputs
        System.out.println("Please enter first name: ");
        register.firstName = scan.nextLine();

        System.out.println("Please enter last name: ");
        register.lastName = scan.nextLine();

        System.out.println("Please enter the username: ");
        String name = scan.nextLine();

        System.out.println("Please enter password: ");
        String password = scan.nextLine();

        System.out.println("Please enter cellphone number: ");
        String cellphone = scan.nextLine();

        // Print registration result message
        System.out.println("\n" + register.registerUser(name, password, cellphone));

        // Stop program if registration failed
        if (!register.registerStatus(name, password, cellphone)) {
            return;
        }

        // Login section
        System.out.println("\n====== Login ======");
        System.out.println("Enter username: ");
        String loginName = scan.nextLine();

        System.out.println("Enter password: ");
        String loginPass = scan.nextLine();

        boolean loginSuccess = register.loginUser(loginName, loginPass);
        System.out.println(register.returnLoginStatus(loginSuccess));
    }
}