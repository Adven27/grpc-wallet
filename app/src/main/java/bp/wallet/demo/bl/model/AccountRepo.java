package bp.wallet.demo.bl.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, AccountId> {
    List<Account> findByAccountIdUser(long user);
}
