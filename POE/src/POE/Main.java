/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package POE;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSys = new Login();
        MessageManager manager = new MessageManager();

        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter username: ");
        String u = scanner.nextLine();
        System.out.print("Enter password: ");
        String p = scanner.nextLine();
        System.out.print("Enter phone: ");
        String ph = scanner.nextLine();
        System.out.println(loginSys.registerUser(u, p, ph));

        System.out.println("\n=== USER LOGIN ===");
        System.out.print("Username: ");
        String lu = scanner.nextLine();
        System.out.print("Password: ");
        String lp = scanner.nextLine();
        boolean loggedIn = loginSys.loginUser(lu, lp);
        System.out.print("First name: ");
        String fn = scanner.nextLine();
        System.out.print("Last name: ");
        String ln = scanner.nextLine();
        System.out.println(loginSys.returnLoginStatus(loggedIn, fn, ln));

        if (!loggedIn) {
            scanner.close();
            return;
        }

        System.out.println("\nWelcome to QuickChat.");
        boolean running = true;
        while (running) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Stored Messages & Reports\n4) Quit");
            String choice = scanner.nextLine();
            if (choice.equals("4")) break;

            switch (choice) {
                case "1":
                    System.out.print("How many messages? ");
                    int count = Integer.parseInt(scanner.nextLine());
                    for (int i = 1; i <= count; i++) {
                        Message msg = new Message(i);
                        System.out.print("Recipient (+27...): ");
                        msg.setRecipientCell(scanner.nextLine());
                        System.out.print("Message content: ");
                        msg.setMessageContent(scanner.nextLine());
                        System.out.println("1) Send | 2) Disregard | 3) Store");
                        int act = Integer.parseInt(scanner.nextLine());
                        manager.addMessage(msg, act == 1 ? "Sent" : (act == 2 ? "Disregarded" : "Stored"));
                        System.out.println(msg.sentMessageOption(act));
                    }
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    System.out.println("a) Longest Message\nb) Search Recipient\nc) Delete by Hash\nd) Display Report");
                    String sub = scanner.nextLine();
                    if (sub.equalsIgnoreCase("a")) System.out.println(manager.getLongestMessage());
                    else if (sub.equalsIgnoreCase("b")) {
                        System.out.print("Recipient: ");
                        System.out.println(manager.searchByRecipient(scanner.nextLine()));
                    } else if (sub.equalsIgnoreCase("c")) {
                        System.out.print("Hash: ");
                        System.out.println(manager.deleteByMessageHash(scanner.nextLine()));
                    } else if (sub.equalsIgnoreCase("d")) System.out.println(manager.displayReport());
                    break;
            }
        }
        scanner.close();
    }
}