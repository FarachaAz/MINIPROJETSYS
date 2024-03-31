package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12000;
    private static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur de chat démarré sur le port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion : " + clientSocket);
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);
                Thread clientThread = new Thread(new ClientHandler(clientSocket, writer));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.clientSocket = socket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message reçu : " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    clients.remove(writer);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }
}

