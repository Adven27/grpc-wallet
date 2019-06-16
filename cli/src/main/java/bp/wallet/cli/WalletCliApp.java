package bp.wallet.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.boot.Banner.Mode.OFF;
import static org.springframework.boot.WebApplicationType.NONE;

@SpringBootApplication
public class WalletCliApp implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WalletCliApp.class).web(NONE).bannerMode(OFF).logStartupInfo(false).run(args);
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {
        List<Integer> params = parseParams(args);
        new Harness(new WalletClient("localhost", 50051))
                .runConcurrentUsers(params.get(0), params.get(1), params.get(2));
    }

    private static List<Integer> parseParams(String... args) {
        List<Integer> result = new LinkedList<>();
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Please, check the arguments.\n " +
                            "users (number of concurrent users emulated),\n " +
                            "concurrent_threads_per_user (number of concurrent requests a user will make),\n " +
                            "rounds_per_thread (number of rounds each thread is executing)\n \n " +
                            "valid example: java -jar cli.jar 5 4 3"
            );
        } else {
            for (String s : args) {
                try {
                    int num = Integer.valueOf(s);
                    if (num < 0) {
                        throw new IllegalArgumentException(
                                "Please, check the arguments.\n " +
                                        "At least one of them is not a valid POSITIVE integer.\n \n" +
                                        "valid example: java -jar cli.jar 5 4 3"
                        );
                    }
                    result.add(num);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                            "Please, check the arguments.\n At least one of them is not a valid integer.\n \n" +
                                    "valid example: java -jar cli.jar 5 4 3"
                    );
                }

            }
        }
        return result;
    }
}
