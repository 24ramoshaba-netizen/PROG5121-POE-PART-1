import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher; 

/**
 * Message class handling message validation , ID generation, hashing and delivery status options
 */

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String status;

    // constructor
    public Message(int messageNumber, String recipientCell, String messageText) {
        this.messageNumber = messageNumber;
         this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    /**generate a unique 10-digit message ID for tracking */
    private String generateMessageID() {
        Random rand = new Random();
        long randomNum = 1000000000L + rand.nextLong(9000000000L);
        return "00" + String.valueOf(randomNum).substring(2);
    }

    /**Ensures that the message ID is not more than 10 characters */
    public boolean checkMessageID(String id) {
        if (id == null) {
            return false;
        }
        return id.length() <= 10;
    }

    public boolean checkMessageID() {
        return checkMessageID(this.messageID);
    }

    /**ensures that the recipient's cell number is no more than 10 characters long and starts with an international code */
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        //maches internatinal prefix (+) and standard cell digits
        String regex = "^\\+\\d{1,3}\\d{7,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);

        if (matcher.matches() && cellNumber.length() <= 12) {
            return "Cellphone number is successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkRecipientCell() {
        return checkRecipientCell(this.recipientCell);
    }

    /**checks if the message is not more than 250 characters */
    public String checkMessageLength(String text) {
        if (text == null) {
            text = "";
        }
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = text.length() - 250;
            return "Message exceeds 250 characters by " + excess + " please reduce the size.";
        }
    }

    public String checkMessageLength() {
        return checkMessageLength(this.messageText);
    }

    /**autogenerates a message hash containing:
     * first 2 numbers of message ID : message number : first and last words in all caps
     */
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2 ) {
            return "00:0:UNKNOWN";
        }
        String prefixID = this.messageID.substring(0, 2);

        String firstWord = "UNKNOWN";
        String lastWord = "UNKNOWN";

        if (this.messageText != null && !this.messageText.trim().isEmpty()) {
            //strip punctuation for accurate word extraction
            String cleaned = this.messageText.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
            String[] words = cleaned.split("\\s+");
            if (words.length > 0) {
                firstWord = words[0];
                lastWord = words[words.length - 1];
            }
        }
        return prefixID + ":" + this.messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    /**allows the user to choose if they want to send, store, or disregard the message */
    public String sentMessage(int choice) {
        
    }
}