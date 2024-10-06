package requests;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Class for handling login requests to the platform.
 */
public class LoginRequests extends BaseRequest {
    /**
     * Sends a login request to the specified endpoint with the provided username and password.
     *
     * @param username the username for login
     * @param password the password for login
     * @param endPoint the URL endpoint for login
     * @throws IOException if an I/O error occurs during the request
     */
    public String platformLoginRequest(String username, String password, String endPoint) throws IOException, InterruptedException {
        HttpURLConnection connection = null;
        String cookieHeader = "";
        try {
            // Initialize the connection
            URL url = new URL(endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setDoOutput(true);

            // Create the POST body (form parameters)
            String postData = String.format("&login_form[username]=%s&login_form[password]=%s&method=login",
                    username, password);

            // Write the POST data to the output stream
            writePostData(connection, postData);

            // Read the response from the input stream
            String response = readResponse(connection);
            System.out.println("The response of the login request is: " + response);
        } catch (IOException e) {
            System.err.println("Error during login request: " + e.getMessage());
            throw e;  // Re-throw the exception for further handling
        } finally {
            if (connection != null) {
                connection.disconnect();  // Ensure connection is closed
            }
        }
        // Check if login was successful (status code 200)
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Login successful!");

            // Extract cookies from the "Set-Cookie" headers
            List<String> cookies = new ArrayList<>();
            Map<String, List<String>> headers = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                if ("Set-Cookie".equalsIgnoreCase(entry.getKey())) {
                    for (String cookie : entry.getValue()) {
                        cookies.add(cookie.split(";", 2)[0]); // Get only "name=value"
                    }
                }
            }
            cookieHeader = String.join("; ", cookies);
            System.out.println("Cookies from login: " + cookieHeader);
        }
        return cookieHeader;
    }
}
