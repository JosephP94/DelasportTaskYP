package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for handling login requests to the platform.
 */
public class LoginRequests {

    private static final String COOKIE = "glc=en; mfl=1; imd=0; sv=european; SESS=7ge4kk3av944cog930p2rt5qi73rm99to5mmbgvil60tgkkl21i9rfpun6d3v1i0mjtccu; CSRF=f7cfe4ced6a2de19f4108f667e3a4e6d958eed54c9c0dab01fe49d69918ff900; cd=16453d6e26";
    private static final String REQUEST_METHOD = "POST";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

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

    /**
     * Writes the POST data to the connection's output stream.
     *
     * @param connection the HttpURLConnection object
     * @param postData  the POST data to be sent
     * @throws IOException if an I/O error occurs
     */
    private void writePostData(HttpURLConnection connection, String postData) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = postData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }

    /**
     * Reads the response from the server.
     *
     * @param connection the HttpURLConnection object
     * @return the server response as a String
     * @throws IOException if an I/O error occurs
     */
    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }


}
