import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**JUnit 5 unit tests using test data in PoE */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login("Kylian", "Mbappe", "kyl_1", "Ch&&sec@ke99!", "+27821234567");
    }

    @Test
    public void testCheckUsernameSuccess() {
        assertTrue(login.checkUsername("kyl_1"));
    }

    @Test
    public void testCheckUsernameFailure() {    
        assertFalse(login.checkUsername("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexitySuccess() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexityFailure() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellphoneNumber() {
        assertTrue(login.checkCellphoneNumber("+27821234567"));
    }
    
    @Test
    public void testCheckCellphoneNumberFailure() {
        assertFalse(login.checkCellphoneNumber("08966553"));
    }

    @Test
    public void testLoginUser() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginUserFailure() {
        assertFalse(login.loginUser("kyl_1", "wrongpassword123!"));
    }

    @Test
    public void testReturnLoginStatus() {
        String expected = "Welcome Kylian Mbappe, it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus(false));
    }
}
