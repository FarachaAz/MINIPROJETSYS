package projet;
import java.util.ArrayList;
import java.util.List;

import com.example.grpc.*;
import io.grpc.stub.StreamObserver;

public class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        // Logique pour envoyer un message au destinataire spécifié
        String status = "Message sent to " + request.getRecipient();
        MessageResponse response = MessageResponse.newBuilder().setStatus(status).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(UserRequest request, StreamObserver<MessagesResponse> responseObserver) {
        // Logique pour récupérer les messages reçus par l'utilisateur donné
        String user = request.getUser();
        // Supposons que nous avons une liste de messages pour l'utilisateur
        List<String> messages = getMessageListForUser(user);
        MessagesResponse response = MessagesResponse.newBuilder().addAllMessages(messages).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private List<String> getMessageListForUser(String user) {
        // Implémentation fictive pour récupérer les messages de l'utilisateur
        // Vous devriez implémenter votre propre logique pour récupérer les messages à partir d'une source de données
        List<String> messages = new ArrayList<>();
        // Supposons que nous ajoutions quelques messages pour l'exemple
        messages.add("Message 1");
        messages.add("Message 2");
        messages.add("Message 3");
        return messages;
    }
}
