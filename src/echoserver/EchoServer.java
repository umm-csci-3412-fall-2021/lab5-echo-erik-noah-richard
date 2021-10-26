package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
        // Start listening on the specified port
        ServerSocket sock = new ServerSocket(portNumber);

        // Run forever, which is common for server style services
        while (true) {
            // Wait until someone connects, thereby requesting a date
            Socket client = sock.accept();

            // Create the input/output streams
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            
            //Read the input, while the end of the input has not been reached, continue
            //reading and writing that to the output
            int input = in.read();
            while(input != -1) {
              out.write(input);
              input = in.read();
            }
            
            //Send whatever else hasn't been sent
            out.flush();
        
            // Close the client socket since we're done.
            client.shutdownOutput();
        }
        // *Very* minimal error handling.
        } catch (IOException ioe) {
        System.out.println("We caught an unexpected exception");
        System.err.println(ioe);
        }
    }
}