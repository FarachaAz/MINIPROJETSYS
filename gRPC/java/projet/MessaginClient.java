package projet;
import com.example.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MessaginClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_ADDRESS, SERVER_PORT)
                .usePlaintext()
                .build();

        MessagingServiceGrpc.MessagingServiceBlockingStub stub = MessagingServiceGrpc.newBlockingStub(channel);

        // Envoi d'un message
        MessageRequest sendRequest = MessageRequest.newBuilder()
                .setSender("CHA")
                .setRecipient("OMAROU")
                .setText("WA BRO!")
                .build();

        MessageResponse sendResponse = stub.sendMessage(sendRequest);
        System.out.println("Send Response: " + sendResponse.getStatus());

        // Récupération des messages pour un utilisateur donné
        UserRequest getMessagesRequest = UserRequest.newBuilder()
                .setUser("OMAROU")
                .build();
       

        MessagesResponse getMessagesResponse = stub.getMessagesForUser(getMessagesRequest);
        System.out.println("Messages for OMAROU:");
        for (String message : getMessagesResponse.getMessagesList()) {
            System.out.println(message);
            
        }

        channel.shutdown();
    }
}
