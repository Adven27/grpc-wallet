package specs;

import bp.wallet.demo.WalletApp;
import bp.wallet.demo.WalletGrpc;
import com.adven.concordion.extensions.exam.ExamExtension;
import io.grpc.ManagedChannel;
import org.concordion.api.AfterSuite;
import org.concordion.api.BeforeSuite;
import org.concordion.api.extension.Extension;
import org.concordion.api.option.ConcordionOptions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import java.util.concurrent.TimeUnit;

import static io.grpc.ManagedChannelBuilder.forAddress;

@RunWith(ConcordionRunner.class)
@ConcordionOptions(declareNamespaces = {"c", "http://www.concordion.org/2007/concordion", "e", ExamExtension.NS})
public class Specs {
    private static final Logger LOG = LoggerFactory.getLogger(Specs.class);
    private static final GenericContainer DB = startDb();
    private static final String JDBC_URL = String.format("jdbc:mysql://localhost:%d/test", DB.getFirstMappedPort());
    private static final String DB_USER = "mysql";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static ManagedChannel CHANNEL;

    protected static WalletGrpc.WalletBlockingStub grpcTester;

    private ConfigurableApplicationContext sut;

    @Extension
    @SuppressWarnings("unused")
    private final ExamExtension exam = new ExamExtension().
            db().url(JDBC_URL).user(DB_USER).password(DB_USER).schema("test").driver(JDBC_DRIVER).end();

    @BeforeSuite
    public void setUp() {
        sut = startSut();
        grpcTester = WalletGrpc.newBlockingStub(openChannel());
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        sut.stop();
        DB.stop();
        closeChannel();
    }

    static ConfigurableApplicationContext startSut() {
        System.setProperty("spring.datasource.url", JDBC_URL);
        System.setProperty("spring.datasource.username", DB_USER);
        System.setProperty("spring.datasource.password", DB_USER);
        System.setProperty("spring.datasource.driver-class-name", JDBC_DRIVER);

        SpringApplication app = new SpringApplication(WalletApp.class);
        app.setAdditionalProfiles("qa");
        return app.run();
    }

    private static GenericContainer startDb() {
        GenericContainer db = new MySQLContainer()
                .withUsername(DB_USER)
                .withPassword(DB_USER)
                .withLogConsumer(new Slf4jLogConsumer(LOG))
                .withEnv("MYSQL_ROOT_HOST", "%");
        db.start();
        return db;
    }

    private ManagedChannel openChannel() {
        CHANNEL = forAddress("localhost", 50051).usePlaintext().build();
        return CHANNEL;
    }

    private void closeChannel() throws InterruptedException {
        CHANNEL.shutdown();
        try {
            assert CHANNEL.awaitTermination(5, TimeUnit.SECONDS) : "channel cannot be gracefully shutdown";
        } finally {
            CHANNEL.shutdownNow();
        }
    }
}