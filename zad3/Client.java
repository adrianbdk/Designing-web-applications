import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private static BufferedReader in;

    void startConnection(String ip, int port) throws IOException {
        while (true) {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
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

    public static void clientHandler() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String message = input.nextLine();
        client.sendMessage(message);

        while (!message.equals("quit")) {
            System.out.print("Enter your message: ");
            message = input.nextLine();
            client.sendMessage(message);
            if (!message.equalsIgnoreCase("quit")) {
                System.out.println(in.readLine());
            }
        }
        client.stopConnection();
        input.close();
    }

    public static void main(String[] args) {
        try {
            clientHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
