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

    //username input loop 
    }
}