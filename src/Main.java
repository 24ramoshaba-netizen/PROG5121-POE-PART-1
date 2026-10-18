import java.util.Scanner;
import java.util.ArrayList;

/**console application entry point */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("============================================");
        System.out.println(" PROG5121 POE: PART 1 - USER REGISTRATION");
        System.out.println("============================================");

        System.out.print("Enter First Name: ");
        userLogin.setFirstName(scanner.nextLine());

         System.out.print("Enter Last Name: ");
        userLogin.setLastName(scanner.nextLine());

        //Username input loop
        while (true) {
            System.out.print("Enter Username (must contain '_' and be <= 5 chars): ");
            String inputUsername = scanner.nextLine();
            if (userLogin.checkUsername(inputUsername)) {
                System.out.println("Username successfully captured");
                userLogin.setUsername(inputUsername);
                break;
            } else {
                System.out.println("Invalid username. Please try again.");
            }
        }
        //Password input loop
        while (true) {
            System.out.print("Enter Password (must contain a special character, a number, and be >= 8 chars): ");
            String inputPassword = scanner.nextLine();
            if (userLogin.checkPasswordComplexity(inputPassword)) {
                System.out.println("Password successfully captured");
                userLogin.setPassword(inputPassword);
                break;
            } else {
                System.out.println("Invalid password. Please try again.");
            }    
        }
        //cellphone number input loop
        while (true) {
            System.out.print("Enter Cellphone Number with international code(e.g +27 123456789): ");
            String inputCell = scanner.nextLine();
            if (userLogin.checkCellphoneNumber(inputCell)) {
                System.out.println("Cellphone number successfully captured");
                userLogin.setCellphoneNumber(inputCell);
                break;
            } else {
                System.out.println("Invalid cellphone number. Please try again.");
            }
        }
        System.out.println("\nRegistration summary:");
        System.out.println(userLogin.registerUser());

        //login process
        System.out.println("\n====================================");
        System.out.println(" PROG5121 POE: PART 1 - USER LOGIN");
        System.out.println("=====================================");

        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        boolean isAuthenticated = userLogin.loginUser(loginUser, loginPass);
        System.out.println("\n" + userLogin.returnLoginStatus(isAuthenticated));


        //PART 2: QUICKCHAT MESAGING FEATURE
        System.out.println("\nWelcome to QuickChat.");
        ArrayList<Message> sentMessagesList = new ArrayList<>();
        int totalMessagesSent = 0;

        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option (1-3): ");

            String choiceStr = scanner.nextLine();
            int menuChoice;
            try {
                menuChoice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            switch (menuChoice) {
                case 1:
                    System.out.println("How many messages do you wish to enter?");
                    int numMessages;
                    try {
                        numMessages = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Returning to menu.");
                        break;
                    }
    
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + "of" + numMessages + " ---");

                        System.out.print("Enter recipient cell phone number (with international code): ");
                        String recipient = scanner.nextLine();

                        Message msg = new Message(i, recipient, "");
                        String cellCheckResult = msg.checkRecipientCell(recipient);
                        System.out.println(cellCheckResult);

                        System.out.print("Enter Message Text (max 250 characters): ");
                        String text = scanner.nextLine();

                        //Re-initialize message with valid text
                        msg = new Message(i, recipient, text);
                        String lengthCheckResult = msg.checkMessageLength(text);
                        System.out.println(lengthCheckResult);

                        if (!lengthCheckResult.equals("Message ready to send.")) {
                            System.out.println("Message skipped due to length restriction.");
                            continue;
                        }

                        System.out.println("Message ID generated: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());

                        System.out.println("\nSelect Action:");
                        System.out.println("1.) Send Message");
                        System.out.println("2.) Disregard Message");
                        System.out.println("3.) Store Message to send later");
                        System.out.print("Enter choice (1-3): ");

                        int actionChoice;
                        try {
                            actionChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            actionChoice = 2; // Default to disregarding the message on invalid input
                        }

                        String actionResult = msg.sentMessage(actionChoice);
                        System.out.println(actionResult);

                        if (actionChoice == 1) {
                            totalMessagesSent++;
                        }

                        sentMessagesList.add(msg);

                        //Display full message details in exact specified order
                        System.out.println("\n--- Final Message Details ---");
                        System.out.println("Message ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("Recipient: " + msg.getRecipientCell());
                        System.out.println("Message: " + msg.getMessageText());
                    }

                    System.out.println("\nTotal number of messages processed: " + sentMessagesList.size());
                    System.out.println("Total messages successfully sent: " + totalMessagesSent);
                    break;
                    
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                    
                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
        }
        
        scanner.close();
    }   

}