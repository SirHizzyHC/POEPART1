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
public class MessageTest {
    @Test
    public void testLength() {
        Message m = new Message(1);
        assertTrue(m.checkMessageLength("Hi Mike!"));
    }
    @Test
    public void testCell() {
        Message m = new Message(1);
        assertTrue(m.checkRecipientCell("+27718693002"));
    }
    @Test
    public void testSendOption() {
        Message m = new Message(1);
        assertEquals("Message successfully sent.", m.sentMessageOption(1));
    }
}
