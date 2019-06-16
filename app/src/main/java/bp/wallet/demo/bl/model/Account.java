package bp.wallet.demo.bl.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @EmbeddedId
    public AccountId accountId;

    public BigDecimal balance;

    @Version
    private long version;

    public Account(long user, String currency, BigDecimal balance) {
        this(new AccountId(user, currency), balance);
    }

    public Account(AccountId accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public BigDecimal deposit(BigDecimal amount) {
        setBalance(getBalance().add(amount));
        return getBalance();
    }

    public BigDecimal withdraw(BigDecimal amount) {
        setBalance(getBalance().subtract(amount));
        return getBalance();
    }

    public Long getUser() {
        return accountId.getUser();
    }

    public String getCurrency() {
        return accountId.getCurrency();
    }
}
