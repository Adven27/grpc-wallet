package specs.deposit;

import bp.wallet.demo.WalletRequest;
import specs.Specs;

public class Deposit extends Specs {

    public String deposit(String user, String amount) {
        return grpcTester.deposit(request(user, amount)).getMessage();
    }

    private WalletRequest request(String user, String amount) {
        return WalletRequest.newBuilder()
                .setAmount(Double.valueOf(amount))
                .setCurrency("EUR")
                .setUser(user)
                .build();
    }
}
