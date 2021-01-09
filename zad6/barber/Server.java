package barber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private ServerSocket serverSocket;

    ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    ArrayList<String> hoursServer = new ArrayList<String>(
            Arrays.asList("10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00",
                    "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00"));

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) new ClientHandler(serverSocket.accept()).start();

    }

    public void stop() throws IOException {
            serverSocket.close();
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            clientHandlers.add(this);
        }

        public void KillConnection() throws IOException {
            in.close();
            out.close();
            clientSocket.close();
        }

        public void updateList(){
            for(ClientHandler client: clientHandlers){
                int x = 1;
                client.out.println(hoursServer);
                System.out.println("New list sent to client" + x);
                x=x+1;
            }
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                //autoFlush - A boolean; if true, the println, printf, or format methods will flush the output buffer
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println(hoursServer);

                String message = "";

                while (!(message = in.readLine()).equalsIgnoreCase("quit")) {
                    System.out.println(message);
                    String finalMessage = message;
                    if(!hoursServer.contains(finalMessage)){
                        hoursServer.add(finalMessage);
                    }else{
                    hoursServer.removeIf(e -> e.equals(finalMessage));}
                    System.out.println(hoursServer);
                    updateList();
                }
                System.out.println("disconnected");

                KillConnection();
            } catch (IOException exc) {
                System.out.println("ERROR - " + exc.getMessage());
            }
        }
    }
}
