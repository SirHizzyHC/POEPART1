/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package POE;

import java.util.List;
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
public class MessageManagerTest {
    @Test
    public void testLongest() {
        MessageManager mm = new MessageManager();
        Message m = new Message(1);
        m.setRecipientCell("+27838884567");
        m.setMessageContent("Where are you? You are late!");
        mm.addMessage(m, "Stored");
        assertEquals("Where are you? You are late!", mm.getLongestMessage());
    }
}