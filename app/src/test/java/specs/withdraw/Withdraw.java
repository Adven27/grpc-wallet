package specs.withdraw;

import bp.wallet.demo.WalletRequest;
import specs.Specs;

public class Withdraw extends Specs {

    public String withdraw(String user, String amount) {
        return grpcTester.withdraw(request(user, amount)).getMessage();
    }

    private WalletRequest request(String user, String amount) {
        return WalletRequest.newBuilder()
                .setAmount(Double.valueOf(amount))
                .setCurrency("EUR")
                .setUser(user)
                .build();
    }
}
