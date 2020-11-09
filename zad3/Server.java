import java.net.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private ServerSocket serverSocket;

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

//    public void stop() throws IOException {
//            serverSocket.close();
//    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void KillConnection() throws IOException {
            in.close();
            out.close();
            clientSocket.close();
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                int Random = ThreadLocalRandom.current().nextInt(1, 99);

                clientName = in.readLine();
                String message = "";
                while (!(message = in.readLine()).equalsIgnoreCase("quit")) {
                    System.out.println(clientName + ": " + message);
                    out.println(clientName + ": " + message);

                }
                System.out.println(clientName + " disconnected");
                out.println(clientName + ": " + message);

                KillConnection();
            } catch (IOException exc) {
                System.out.println("ERROR - " + exc.getMessage());
            }
        }
    }
}
