package specs.balance;

import bp.wallet.demo.BalanceRequest;
import org.concordion.api.MultiValueResult;
import specs.Specs;

import static org.concordion.api.MultiValueResult.multiValueResult;

public class Balance extends Specs {

    public MultiValueResult balance(String user) {
        MultiValueResult result = multiValueResult();
        grpcTester.balance(request(user)).getBalancesMap().forEach((key, val) -> result.with(key.toLowerCase(), val));
        return result;
    }

    private BalanceRequest request(String user) {
        return BalanceRequest.newBuilder().setUser(user).build();
    }
}
