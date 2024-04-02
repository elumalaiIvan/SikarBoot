import java.io.*;
import java.net.*;
public class SikarServer {
    private final int PORT = 8080;
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleRequest(clientSocket); // Handle incoming request
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            // Read the HTTP request
            String requestLine = reader.readLine();
            System.out.println("Request: " + requestLine);

            // Send HTTP response
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello, world!";
            writer.write(response);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}