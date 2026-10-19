/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

import java.nio.file.Files;
import java.nio.file.Path;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit tests for the Message class (Part 2).
 */
public class MessageNGTest {

    private Message msg;

    // Test data from the brief
    private final String message1 = "Hi Jake, can you join us for dinner tonight?";
    private final String message2 = "Hi Neo, did you receive the payment?";

    @BeforeMethod
    public void setUp() {
        msg = new Message();
    }

    @Test
    public void messageLengthSuccess() {
        assertEquals(msg.checkMessageLength(message1), "Message ready to send.");
    }

    @Test
    public void messageLengthFailure() {
        String longMessage = "a".repeat(260);
        assertEquals(msg.checkMessageLength(longMessage),
                "Message exceeds 250 characters by 10; please reduce the size.");
    }

    @Test
    public void recipientNumberCorrectlyFormatted() {
        assertEquals(msg.checkRecipientCell("+27718693002"),
                "Cell phone number successfully captured.");
    }

    @Test
    public void recipientNumberIncorrectlyFormatted() {
        assertEquals(msg.checkRecipientCell("08575975889"),
                "Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.");
    }

    @Test
    public void messageHashCorrectForTestCase1() {
        assertEquals(msg.createMessageHash("0012345678", 0, message1), "00:0:HITONIGHT");
    }

    @Test
    public void messageHashesCorrectInLoop() {
        String[] ids = {"0012345678", "0112345678"};
        String[] texts = {message1, message2};
        String[] expected = {"00:0:HITONIGHT", "01:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            assertEquals(msg.createMessageHash(ids[i], i, texts[i]), expected[i]);
        }
    }

    @Test
    public void messageIDIsCreated() {
        String id = msg.generateMessageID();

        assertEquals(id.length(), 10);
        assertTrue(msg.checkMessageID(id));
        assertEquals(msg.messageIDStatus(id), "Message ID generated: " + id);
    }

    @Test
    public void messageIDLongerThanTenCharactersFails() {
        assertFalse(msg.checkMessageID("12345678901"));
    }

    @Test
    public void sentMessageSend() {
        assertEquals(msg.SentMessage(1), "Message successfully sent.");
    }

    @Test
    public void sentMessageDisregard() {
        assertEquals(msg.SentMessage(2), "Press 0 to delete the message.");
    }

    @Test
    public void sentMessageStore() {
        assertEquals(msg.SentMessage(3), "Message successfully stored.");
    }

    @Test
    public void totalMessagesStartsAtZero() {
        assertEquals(msg.returnTotalMessages(), 0);
    }

    @Test
    public void storeMessageWritesJsonFile() throws Exception {
        msg.messageIds.add("0012345678");
        msg.msgHash.add("00:0:HITONIGHT");
        msg.recipientNumber.add("+27718693002");
        msg.messages.add(message1);
        msg.messageStatus.add("Stored");

        msg.storeMessage();

        Path file = Path.of("storedMessages.json");
        String content = Files.readString(file);
        assertTrue(content.contains("\"messageID\": \"0012345678\""));
        assertTrue(content.contains("\"message\": \"" + message1 + "\""));
        Files.deleteIfExists(file);
    }

}