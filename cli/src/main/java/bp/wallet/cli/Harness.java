package bp.wallet.cli;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.Executors.newFixedThreadPool;

@Slf4j
@RequiredArgsConstructor
class Harness {
    private final WalletClient client;

    private void roundA(String user) {
        log.info("User " + user + " at Round A");
        client.deposit(user, 100.0, "USD");
        client.withdraw(user, 200.0, "USD");
        client.deposit(user, 100.0, "EUR");
        client.getBalance(user);
        client.withdraw(user, 100.0, "USD");
        client.getBalance(user);
        client.withdraw(user, 100.0, "USD");
    }

    private void roundB(String user) {
        log.info("User " + user + " at Round B");
        client.withdraw(user, 100.0, "GBP");
        client.deposit(user, 300.0, "GBP");
        client.withdraw(user, 100.0, "GBP");
        client.withdraw(user, 100.0, "GBP");
        client.withdraw(user, 100.0, "GBP");
    }

    private void roundC(String user) {
        log.info("User " + user + " at Round C");
        client.getBalance(user);
        client.deposit(user, 100.0, "USD");
        client.deposit(user, 100.0, "USD");
        client.withdraw(user, 100.0, "USD");
        client.deposit(user, 100.0, "USD");
        client.getBalance(user);
        client.withdraw(user, 200.0, "USD");
        client.getBalance(user);
    }

    private void roundD(String user) {
        log.info("User " + user + " at Round D");
        client.deposit(user, 100.0, "USD");
        client.withdraw(user, 100.0, "USD");
        client.getBalance(user);
    }

    private void executeRounds(String user, int numRounds) {
        for (int i = 0; i < numRounds; i++) {
            switch (new Random().nextInt(3)) {
                case 0:
                    roundA(user);
                    break;
                case 1:
                    roundB(user);
                    break;
                default:
                    roundC(user);
            }
        }
    }

    void runConcurrentUsers(final int users, final int threads, final int rounds) throws ExecutionException, InterruptedException {
        final ExecutorService executor = newFixedThreadPool(100, threadName("u-%d"));
        CompletableFuture.allOf(
                IntStream.range(0, users)
                        .mapToObj(user -> runAsync(() -> runConcurrentThreadsPerUser(user, threads, rounds), executor))
                        .toArray(CompletableFuture[]::new)
        ).thenAccept(aVoid -> log.info("END USERS")).get();
        executor.shutdownNow();
        client.shutdown();
    }

    private void runConcurrentThreadsPerUser(int user, int threads, int rounds) {
        final ExecutorService executor = newFixedThreadPool(100, threadName(currentThread().getName() + "-t-%d"));
        CompletableFuture.allOf(
                IntStream.range(0, threads)
                        .mapToObj(ignore -> runAsync(() -> executeRounds(String.valueOf(user), rounds), executor))
                        .toArray(CompletableFuture[]::new)
        ).thenAccept(aVoid -> log.info("END ROUNDS")).join();
        executor.shutdownNow();
    }

    private ThreadFactory threadName(String format) {
        return new ThreadFactoryBuilder().setNameFormat(format).build();
    }
}