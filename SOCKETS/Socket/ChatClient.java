package Socket;

import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connecté au serveur de chat sur " + SERVER_ADDRESS + ":" + SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Lecture de l'entrée utilisateur
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entrez votre pseudo : ");
            String pseudo = userInputReader.readLine();
            writer.println(pseudo);

            // Lecture des messages du serveur et affichage
            String serverMessage;
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
