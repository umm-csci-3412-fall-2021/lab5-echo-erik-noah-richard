package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
        server = "127.0.0.1";
        } else {
        server = args[0];
        }

        try {
        // Connect to the server
        Socket socket = new Socket(server, portNumber);

        // Get the input/output streams so we can read from that socket
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // Read the input from the user and send it to the server,
        // Read the output from the server and send it to the user.
        int userInput = System.in.read();
        while(userInput != -1) {
            output.write(userInput);
            int serverInput = input.read();
            System.out.write(serverInput);
            userInput = System.in.read();
        }

        // Flush everything
        output.flush();
        System.out.flush();

        // Close the socket
        socket.shutdownOutput();
        socket.close();

        // Provide some minimal error handling.
        } catch (ConnectException ce) {
        System.out.println("We were unable to connect to " + server);
        System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
        System.out.println("We caught an unexpected exception");
        System.err.println(ioe);
        }
    
    }
}