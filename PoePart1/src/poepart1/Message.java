 /*;
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Student
 */
public class Message {

    ArrayList<String> messageIds = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>();

    Scanner input = new Scanner(System.in);
    Random random = new Random();

    int numMessagesSent = 0;

    private static final String RECIPIENT_OK = "Cell phone number successfully captured.";
    private static final String RECIPIENT_BAD = "Cell phone number is incorrectly formatted or does not "
            + "contain an international code. Please correct the number and try again.";
    private static final String MESSAGE_OK = "Message ready to send.";

    public void sendingMessages() {
        // System.out.println("Testing");

        System.out.print("How many messages do you want to send?");
        int numberOfMessages = input.nextInt();

        input.nextLine();

        for (int i = 1; i <= numberOfMessages; i++) {

            System.out.println("MESSAGE: " + i);

            String messageID = generateMessageID();
            System.out.println(messageIDStatus(messageID));

            System.out.print("Enter recipient cell number!!");
            String recipient = input.nextLine();

            String recipientResult = checkRecipientCell(recipient);

            if (!recipientResult.equals(RECIPIENT_OK)) {
                System.out.println(recipientResult);
                messageIds.remove(messageID);
                i--;
                continue;
            }
            System.out.println(recipientResult);
            recipientNumber.add(recipient);

            System.out.print("Enter your message!!");
            String messageText = input.nextLine();

            String lengthResult = checkMessageLength(messageText);

            if (messageText.length() > 250
                    || messageText.trim().isEmpty()) {
                System.out.println(messageText.length() > 250
                        ? lengthResult : "Your message length should be between 1 to 250");
                messageIds.remove(messageID);
                recipientNumber.remove(recipientNumber.size() - 1);
                i--;
                continue;
            }
            System.out.println(lengthResult);

            messages.add(messageText);

            String messageHash = createMessageHash(messageID,
                    i, messageText);

            msgHash.add(messageHash);

            String status = SentMessage();

            if (messages.size() > messageStatus.size()) {
                messageStatus.add(status);
            }

            if (status.equals("Sent")) {
                numMessagesSent++;
            }

            if (status.equals("Stored")) {
                storeMessage();
            }

        }

        printMessages();
        System.out.println("Total messages sent: " + returnTotalMessages());

    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }

    /*For(int i = 0; i < messages.size(); i++) {
        System.out.println("\n=====Message details=====");
        System.out.println("Mesage Id:" + messageIds.get(i));
        System.out.println("Recipient:" + recipientNumber.get(i));
        System.out.println("Message:" + messages.get(i));
        System.out.println("Messages Has:" + msgHash.get(i));

    }*/
    public String generateMessageID() {

        String messageID;

        do {
            long number = 100000000L + random.nextLong(900000000L);

            messageID = String.valueOf(number);

        } while (messageIds.contains(messageID));

        messageIds.add(messageID);

        return messageID;
    }

    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    public String messageIDStatus(String messageID) {
        if (checkMessageID(messageID)) {
            return "Message ID generated: " + messageID;
        }
        return "Message ID is not valid.";
    }

    public String checkMessageLength(String messageText) {
        if (messageText.length() > 250) {
            return "Message exceeds 250 characters by " + (messageText.length() - 250)
                    + "; please reduce the size.";
        }
        return MESSAGE_OK;
    }

    public String checkRecipientCell(String cellphone) {
        if (cellphone.matches("^\\+27[0-9]{9}$")
                || cellphone.matches("^0[0-9]{9}$")) {

            return RECIPIENT_OK;

        } else {

            return RECIPIENT_BAD;
        }
    }

    public String createMessageHash(String messageID,
            int messageNumber,
            String messageText) {

        String firstTwoNumbers
                = messageID.substring(0, 2);

        String trimmedMessage = messageText.trim();
        String[] words = trimmedMessage.split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        String hash = firstTwoNumbers
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        return hash.toUpperCase();
    }

    public String SentMessage() {

        System.out.println("\nChoose what you what do with this message");
        System.out.println("1, Send Message");
        System.out.println("2, Disregard Message");
        System.out.println("3, Store Message to send later");

        System.out.print("Enter your choice");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 1 -> {
                System.out.println(SentMessage(1));
                return "Sent";
            }

            case 2 -> {
                System.out.println(SentMessage(2));
                int deleteChoice = input.nextInt();

                input.nextLine();

                if (deleteChoice == 0) {
                    System.out.println("Message deleted");

                    int lastIndex = messages.size() - 1;

                    messageIds.remove(lastIndex);
                    recipientNumber.remove(lastIndex);
                    messages.remove(lastIndex);
                    msgHash.remove(lastIndex);

                    return "Disregarded";

                } else {
                    System.out.println("Invalid option!");
                    return "Disregarded";
                }

            }

            case 3 -> {
                System.out.println(SentMessage(3));
                return "Stored";
            }

            default -> {
                System.out.println("Invalid option");
                return "Disregarded";
            }

        }

    }

    public String SentMessage(int choice) {
        return switch (choice) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete the message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option";
        };
    }

    public String printMessages() {

        StringBuilder allMessages = new StringBuilder();
        System.out.println("\n===== All messages =====");

        for (int i = 0; i < messages.size(); i++) {

            allMessages.append("\nMessage Number: ").append(i + 1);
            allMessages.append("\nMessage ID: ").append(messageIds.get(i));
            allMessages.append("\nMessage Hash: ").append(msgHash.get(i));
            allMessages.append("\nRecipient: ").append(recipientNumber.get(i));
            allMessages.append("\nMessage: ").append(messages.get(i));
            allMessages.append("\nStatus: ").append(messageStatus.get(i));

            System.out.println("\n=====Message details=====");
            System.out.println("Message ID: " + messageIds.get(i));
            System.out.println("Message Hash: " + msgHash.get(i));
            System.out.println("Recipient: " + recipientNumber.get(i));
            System.out.println("Message: " + messages.get(i));

        }

        return allMessages.toString();

    }

    public void storeMessage() {

        StringBuilder json = new StringBuilder("\n");
        boolean firstEntry = true;

        for (int i = 0; i < messages.size() && i < messageStatus.size(); i++) {
            if (messageStatus.get(i).equals("Stored")) {
                if (!firstEntry) {
                    json.append(",\n");
                }
                json.append(" {\n");
                json.append("    \"messageID\": \"").append(escapeJson(messageIds.get(i))).append("\",\n");
                json.append("    \"messageHash\": \"").append(escapeJson(msgHash.get(i))).append("\",\n");
                json.append("    \"recipient\": \"").append(escapeJson(recipientNumber.get(i))).append("\",\n");
                json.append("    \"message\": \"").append(escapeJson(messages.get(i))).append("\"\n");
                json.append(" }\n");
                firstEntry = false;
            }
        }
        json.append("\n");

        try (FileWriter writer = new FileWriter("storedMessages.json")) {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("Could not store the message in the JSON file.");
        }
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

}//end of class