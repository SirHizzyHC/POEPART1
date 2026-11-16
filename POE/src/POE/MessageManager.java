/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POE;

/**
 *
 * @author Student
 */
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> disregardedMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIDs = new ArrayList<>();

    public void addMessage(Message msg, String status) {
        messageIDs.add(msg.getMessageID());
        messageHashes.add(msg.getMessageHash());
        if (status.equalsIgnoreCase("Sent")) {
            sentMessages.add(msg);
        } else if (status.equalsIgnoreCase("Disregarded") || status.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(msg);
        } else if (status.equalsIgnoreCase("Stored")) {
            storedMessages.add(msg);
        }
    }

    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }

    public String getLongestMessage() {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        if (all.isEmpty()) return "No messages available.";
        Message longest = all.get(0);
        for (Message m : all) {
            if (m.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = m;
            }
        }
        return longest.getMessageContent();
    }

    public String searchByMessageID(String id) {
        for (Message m : getAllMessages()) {
            if (m.getMessageID().equals(id)) return m.getMessageContent();
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        for (Message m : getAllMessages()) {
            if (m.getRecipientCell().equals(recipient)) {
                result.append("\"").append(m.getMessageContent()).append("\" ");
            }
        }
        return result.toString().trim();
    }

    public String deleteByMessageHash(String hash) {
        for (Message m : getAllMessages()) {
            if (m.getMessageHash().equalsIgnoreCase(hash)) {
                sentMessages.remove(m);
                storedMessages.remove(m);
                disregardedMessages.remove(m);
                messageHashes.remove(m.getMessageHash());
                messageIDs.remove(m.getMessageID());
                return "Message: \"" + m.getMessageContent() + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public String displayReport() {
        StringBuilder report = new StringBuilder("=== SENT MESSAGES REPORT ===\n");
        for (Message m : sentMessages) {
            report.append("Message Hash: ").append(m.getMessageHash()).append("\n");
            report.append("Recipient: ").append(m.getRecipientCell()).append("\n");
            report.append("Message: ").append(m.getMessageContent()).append("\n--------------------\n");
        }
        return report.toString();
    }

    private List<Message> getAllMessages() {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);
        return all;
    }
}