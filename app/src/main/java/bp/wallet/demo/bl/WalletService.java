package bp.wallet.demo.bl;

import bp.wallet.demo.bl.model.Account;
import bp.wallet.demo.bl.model.AccountId;
import bp.wallet.demo.bl.model.AccountRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toMap;

@Service
public class WalletService {
    private final AccountRepo repo;

    public WalletService(AccountRepo repo) {
        this.repo = repo;
    }

    public void deposit(long user, BigDecimal amount, String currency) {
        requireNotNegative(amount);
        Account account = repo.findById(new AccountId(user, currency)).orElse(new Account(user, currency, ZERO));
        account.deposit(amount);
        repo.save(account);
    }

    public void withdraw(long user, BigDecimal amount, String currency) {
        requireNotNegative(amount);
        Account account = repo.findById(new AccountId(user, currency)).orElseThrow(InsufficientFunds::new);
        if (account.withdraw(amount).doubleValue() < 0.0) {
            throw new InsufficientFunds();
        }
        repo.save(account);
    }

    public Map<String, BigDecimal> getBalance(int user) {
        return repo.findByAccountIdUser(user).stream().collect(toMap(Account::getCurrency, Account::getBalance));
    }

    private static void requireNotNegative(BigDecimal amount) {
        if (amount.doubleValue() < 0) {
            throw new NegativeAmount();
        }
    }

    public static class NegativeAmount extends RuntimeException {
    }

    public static class InsufficientFunds extends RuntimeException {
    }
}
