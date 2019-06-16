package bp.wallet.cli;

import bp.wallet.demo.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WalletClient {
    private final ManagedChannel channel;
    private final WalletGrpc.WalletBlockingStub blockingStub;

    public WalletClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    private WalletClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = WalletGrpc.newBlockingStub(channel);
    }

    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deposit(String user, double amount, String currency) {
        try {
            log.info("user " + user + " will try to deposit " + amount + currency);
            WalletResponse response = blockingStub.deposit(
                    WalletRequest.newBuilder().setUser(user).setAmount(amount).setCurrency(currency).build()
            );
            log.info("Deposit status: " + response.getMessage());

            if (needRetry(response)) {
                log.info("retrying deposit...");
                deposit(user, amount, currency);
            }
        } catch (StatusRuntimeException e) {
            log.error("RPC failed: {}", e.getStatus());
        }
    }

    public void withdraw(String user, double amount, String currency) {
        try {
            log.info("user " + user + " will try to withdraw " + amount + currency);
            WalletResponse response = blockingStub.withdraw(
                    WalletRequest.newBuilder().setUser(user).setAmount(amount).setCurrency(currency).build()
            );
            log.info("Withdraw status: " + response.getMessage());

            if (needRetry(response)) {
                log.info("retrying withdraw...");
                withdraw(user, amount, currency);
            }
        } catch (StatusRuntimeException e) {
            log.error("RPC failed: {}", e.getStatus());
        }
    }

    public Map<String, Double> getBalance(String user) {
        Map<String, Double> balances = new HashMap<>();
        try {
            BalanceResponse response = blockingStub.balance(BalanceRequest.newBuilder().setUser(user).build());
            balances.putAll(response.getBalancesMap());
        } catch (StatusRuntimeException e) {
            log.error("RPC failed: {}", e.getStatus());
            return balances;
        }
        log.info("Balances: " + balances);
        return balances;
    }

    private boolean needRetry(WalletResponse response) {
        return "stale_state".equals(response.getMessage());
    }

}
