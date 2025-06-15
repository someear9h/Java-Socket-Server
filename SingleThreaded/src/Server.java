package SingleThreaded.src;

import java.io.*;
import java.net.*;

public class Server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        
        while (true) {
            try (Socket acceptedConnection = socket.accept()) {
                System.out.println("Connection accepted from client " + 
                    acceptedConnection.getRemoteSocketAddress());
                
                // Set up I/O streams
                PrintWriter toClient = new PrintWriter(
                    acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(acceptedConnection.getInputStream()));
                
                // Read client message FIRST
                String clientMessage = fromClient.readLine();
                System.out.println("Client says: " + clientMessage);  // 👈 This prints the client's message
                
                // Then respond
                toClient.println("Hello from the server");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Server().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}