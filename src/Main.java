import java.util.Scanner;

/**console application entry point */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();
        
        System.out.println("===USER REGISTRATION===");

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
        System.out.println("\n===USER LOGIN===");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        boolean isAuthenticated = userLogin.loginUser(loginUser, loginPass);
        System.out.println("\n" + userLogin.returnLoginStatus(isAuthenticated));

        scanner.close();
    }

}