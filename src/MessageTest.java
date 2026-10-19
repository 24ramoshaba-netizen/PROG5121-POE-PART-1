import org.junit.jupiter.api.BeforeEach;         
import org.junit.jupiter.api.Test;               
import static org.junit.jupiter.api.Assertions.*;

/**Message tests for PART 2 message validation, hashing, and status handling */
public class MessageTest {
    private Message message1;
    private Message message2;

    @BeforeEach 
    public void setUp() {
        //Test data Case 1
        message1 = new Message(0, "+27718693002","Hi Mike, can you join us for dinner tonight?" );
        //Test data Case 2
        message2 = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
    }

    @Test 
    public void testCheckMessageIDValid() {
        assertTrue(message1.checkMessageID(message1.getMessageID()));
    }

    @Test 
    public void testCheckRecipientCellSuccess() {
        String result = message1.checkRecipientCell();
        assertEquals( "Cellphone number is successfully captured.", result);
    }

    @Test                                                                 
       public void testCheckRecipientCellFailure() {                         
        String result = message2.checkRecipientCell();                    
        assertTrue( result.contains("incorrectly formatted or does not contain an international code"));
    }

    @Test
    public void testMessageLengthSuccess() {
        String result = message1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test                                                                 
    public void testMessageLengthFailure() {                         
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longText.append("a");
        }
        Message longMsg = new Message(2, "+27821234567", longText.toString());
        String result = longMsg.checkMessageLength();
        assertTrue(result.contains("exceeds 250 characters"));
    }    

    @Test                                                                 
    public void testCreatesMessageHash() {
        //Hash format verification based on test data 1                         
        String hash = message1.createMessageHash();                    
        assertTrue( hash.contains("HITONIGHT"));
    }
    
    @Test                                                                 
    public void testSentMessageSendOption() {                         
        String response = message1.sentMessage(1);                    
        assertEquals( "Message successfully sent.", response);
    }    

    @Test                                                           
    public void testSentMessageDiscardOption() {                       
        String response = message2.sentMessage(2);                  
        assertEquals( "Press 0 to delete the message", response);  
    }
    
    @Test                                                           
    public void testSentMessageStoreOption() {                       
        String response = message2.sentMessage(3);                  
        assertEquals( "Message successfully stored.", response);  
    }   

}                                                               