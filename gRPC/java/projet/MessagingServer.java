package projet;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MessagingServer {
    private static final int PORT = 9090;
    private Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT).addService(new MessagingServiceImpl()).build();
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void awaitTermination() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MessagingServer server = new MessagingServer();
        server.start();
        server.awaitTermination();
    }
}
