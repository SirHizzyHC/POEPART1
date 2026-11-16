 /*;
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Pattern;

public class Message {
    private String messageID;
    private int numMessagesSent;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private String deliveryStatus;

    public Message(int messageNumber) {
        this.numMessagesSent = messageNumber;
        this.messageID = generateMessageID();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public boolean checkRecipientCell(String phoneNumber) {
        if (phoneNumber == null) return false;
        /* ATTRIBUTION: Regex pattern structure adapted from standard international E.164 phone validation rules 
           sourced from Oracle Java Documentation (https://docs.oracle.com/). */
        String regex = "^\\+\\d{1,3}\\d{7,10}$";
        return Pattern.matches(regex, phoneNumber);
    }

    public boolean checkMessageLength(String message) {
        if (message == null) return false;
        return message.length() <= 250;
    }

    public String createMessageHash(String message) {
        if (messageID == null || messageID.length() < 2) return "";
        String firstTwoId = messageID.substring(0, 2);
        String cleanedMessage = message.replaceAll("[^a-zA-Z ]", "").trim();
        String[] words = cleanedMessage.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (firstTwoId + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessageOption(int choice) {
        switch (choice) {
            case 1:
                this.deliveryStatus = "Sent";
                return "Message successfully sent.";
            case 2:
                this.deliveryStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.deliveryStatus = "Stored";
                storeMessageToJson();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public void storeMessageToJson() {
        /* ATTRIBUTION: JSON file-writing technique implemented using standard java.io.FileWriter 
           referenced from standard Java I/O libraries (https://docs.oracle.com/javase/tutorial/essential/io/). */
        String jsonContent = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"recipientCell\": \"" + recipientCell + "\",\n" +
                "  \"messageContent\": \"" + messageContent + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"deliveryStatus\": \"" + deliveryStatus + "\"\n" +
                "}";
        try (FileWriter file = new FileWriter("stored_messages.json", true)) {
            file.write(jsonContent + ",\n");
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
        }
    }

    public static String readStoredJsonFile() {
        try {
            File f = new File("stored_messages.json");
            if (!f.exists()) return "No stored JSON file found.";
            return new String(Files.readAllBytes(Paths.get("stored_messages.json")));
        } catch (IOException e) {
            return "Error reading JSON: " + e.getMessage();
        }
    }

    public void setRecipientCell(String recipientCell) { this.recipientCell = recipientCell; }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        this.messageHash = createMessageHash(messageContent);
    }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageContent() { return messageContent; }
    public int getNumMessagesSent() { return numMessagesSent; }
    public String getDeliveryStatus() { return deliveryStatus; }
}