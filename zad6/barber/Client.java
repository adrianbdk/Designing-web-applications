package barber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    PrintWriter out;
    BufferedReader in;


    void startConnection(String ip, int port) throws IOException {
        while (true) {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            //autoFlush - A boolean; if true, the println, printf, or format methods will flush the output buffer
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            break;
        }
    }

    void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void sendMessage(String msg) {
        try {
            out.println(msg);
        } catch (Exception exc) {
            System.out.println("ERROR - " + exc);
        }
    }

}
