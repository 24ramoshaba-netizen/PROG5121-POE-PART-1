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

    /**Check if the username contains an underscore and is no more than 5 characters long*/
    public boolean checkUsername(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkUserName(){
        return checkUserName(this.username);
    }

    /**Validates that the password meets complexity requirements:
     * - At least 8 characters long
     * - Contains at least one Capital letter
     * - contains a number
     * - contains a special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasDigit && hasSpecial;
    }

    public boolean checkPasswordComplexity(){
        return checkPasswordComplexity(this.password);
    }

    /**Validates the cellphone number format using regular expressions
     * 
     * Reference:
     * Regular expression pattern adapted from OWASP Validation Regex Guidelines:
     * and Oracle Java Pattern documentation:
     * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
    */
   public boolean checkCellphoneNumber(String cellphoneNumber) {
        if (cellphoneNumber == null) {
            return false;
        }
        // Matches international country code (+27) and main phone number sequence
        String regex = "^\\+\\d{1,3}\\d{1,9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellphoneNumber);
        
        return matcher.matches() && cellphoneNumber.length() <= 12;
    }

    public boolean checkCellphoneNumber(){
        return checkCellphoneNumber(this.cellphoneNumber);
    }

}