package bp.wallet.demo;

import bp.wallet.demo.grpc.WalletServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletApp implements CommandLineRunner {
    private WalletServer server;
    private final int port;

    public WalletApp(WalletServer server, @Value("${wallet.server.port:50051}") int port) {
        this.server = server;
        this.port = port;
    }

    public static void main(String[] args) {
        SpringApplication.run(WalletApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        server.start(port);
    }
}
