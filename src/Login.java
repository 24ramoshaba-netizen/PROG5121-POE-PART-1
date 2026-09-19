import java.util.regex.Matcher;
import java.util.regex.Pattern;  

/**login class handling login functionality */
public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellphoneNumber;

    //default constructor
    public Login() {
    }

    public Login(String firstName, String lastName, String username, String password, String cellphoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    /** Getters and Setters for the class attributes */
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCellphoneNumber() { return cellphoneNumber; }
    public void setCellphoneNumber(String cellphoneNumber) { this.cellphoneNumber = cellphoneNumber; }

}