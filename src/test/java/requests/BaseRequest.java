package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class BaseRequest {
    protected static final String REQUEST_METHOD = "POST";
    /**
     * Writes the POST data to the connection's output stream.
     *
     * @param connection the HttpURLConnection object
     * @param postData  the POST data to be sent
     * @throws IOException if an I/O error occurs
     */
    protected void writePostData(HttpURLConnection connection, String postData) throws IOException {
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
    protected String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("The response from "+connection.getURL()+" is: " + response);
        }
        return response.toString();
    }

    /**
     * Sends an empty request body.
     *
     * @param connection the HttpURLConnection object
     * @throws IOException if an I/O error occurs
     */
    protected void sendEmptyRequestBody(HttpURLConnection connection) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = "".getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }
}
