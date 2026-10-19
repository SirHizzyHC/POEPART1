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

        System.out.println("\n===== Register =====");

        System.out.print("Please enter the first name: ");
        String firstName = scan.nextLine();

        System.out.print("Please enter the last name: ");
        String lastName = scan.nextLine();

        //Username input and checker
        System.out.print("Please enter the username: ");
        String userName = scan.nextLine();

        //password input and checker
        System.out.print("Enter your password: ");
        String password = scan.nextLine();

        //cellphone number check and input
        System.out.print("Enter your numbers: ");
        String cellphone = scan.nextLine();

        String registerMessage = register.registerUser(firstName, lastName, userName, password, cellphone);

        System.out.println(registerMessage);

        if (!register.registerStatus(userName, password, cellphone)) {
            return;
        }

        //Login
        String passWord = null;
        System.out.println("\n===== Login =====");

        final int MAX_LOGIN_ATTEMPTS = 3;
        int loginAttempts = 1;

        System.out.print("Enter your username: ");
        String loginName = scan.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scan.nextLine();

        boolean loginStatus = register.loginUser(loginName, loginPassword);

        while (!loginStatus) {
            System.out.println(register.returnLoginStatus(loginStatus));

            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                System.out.println("Too many failed login attempts. Goodbye!");
                return;
            }
            loginAttempts++;

            System.out.print("Enter your username: ");
            loginName = scan.nextLine();

            System.out.print("Enter your password: ");
            loginPassword = scan.nextLine();

            loginStatus = register.loginUser(loginName, loginPassword);
        }
        System.out.println(register.returnLoginStatus(loginStatus));

        System.out.println("\nWelcome to QuickChat.");

        Message msg = new Message();

        int option;
        do {
            System.out.println("\n===== Select any of the option below =====");
            System.out.println("(1) Send Messages");
            System.out.println("(2) Show recently sent messages");
            System.out.println("(3) Quit");

            System.out.println("Enter your choice");
            option = scan.nextInt();

            switch (option) {
                case 1 -> msg.sendingMessages();
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> System.out.println("Thank you, Goodbye!");
                default -> System.out.println("Invalid option!");
            }

        } while (option != 3);
        //end of class
    }

}