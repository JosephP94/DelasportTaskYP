package variables;

/**
 * Class to hold login credentials for the application.
 */
public class LoginVariables {

    // Encapsulated fields for username and password
    private final String userName;
    private final String password;

    /**
     * Constructor to initialize login credentials.
     *
     * @param userName the username for login
     * @param password the password for login
     */
    public LoginVariables(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
