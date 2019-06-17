package bp.wallet.demo.bl;

import bp.wallet.demo.bl.model.Account;
import bp.wallet.demo.bl.model.AccountId;
import bp.wallet.demo.bl.model.AccountRepo;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WalletServiceTest {
    private static final AccountId ACC_EUR = new AccountId(1L, "EUR");
    private static final AccountId ACC_USD = new AccountId(1L, "USD");

    private AccountRepo repo = mock(AccountRepo.class);
    private WalletService sut = new WalletService(repo);

    @Test
    public void depositExistedAccount_shouldSaveIncreasedBalance() {
        when(repo.findById(ACC_EUR)).thenReturn(Optional.of(accountWithBalance(TEN)));

        sut.deposit(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency());

        verify(repo).findById(ACC_EUR);
        verify(repo).save(eq(accountWithBalance(TEN.add(TEN))));
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void depositNotExistedAccount_shouldSaveAccount() {
        when(repo.findById(ACC_EUR)).thenReturn(empty());

        sut.deposit(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency());

        verify(repo).findById(ACC_EUR);
        verify(repo).save(eq(accountWithBalance(TEN)));
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void depositNegativeAmount_shouldReturnError() {
        when(repo.findById(ACC_EUR)).thenReturn(Optional.of(accountWithBalance(TEN)));

        assertThatThrownBy(() -> sut.deposit(ACC_EUR.getUser(), TEN.negate(), ACC_EUR.getCurrency()))
                .isExactlyInstanceOf(WalletService.NegativeAmount.class);
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void depositConcurrentAccountCreation_shouldReturnError() {
        when(repo.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThatThrownBy(() -> sut.deposit(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency()))
            .isExactlyInstanceOf(WalletService.StaleState.class);
    }

    @Test
    public void depositConcurrentModification_shouldReturnError() {
        when(repo.save(any())).thenThrow(ObjectOptimisticLockingFailureException.class);

        assertThatThrownBy(() -> sut.deposit(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency()))
            .isExactlyInstanceOf(WalletService.StaleState.class);
    }

    @Test
    public void withdrawExistedAccount_shouldSaveDecreasedBalance() {
        when(repo.findById(ACC_EUR)).thenReturn(Optional.of(accountWithBalance(TEN)));

        sut.withdraw(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency());

        verify(repo).findById(ACC_EUR);
        verify(repo).save(eq(accountWithBalance(ZERO)));
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void withdrawNotExistedAccount_shouldReturnError() {
        when(repo.findById(ACC_EUR)).thenReturn(empty());

        assertThatThrownBy(() -> sut.withdraw(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency()))
                .isExactlyInstanceOf(WalletService.InsufficientFunds.class);
        verify(repo).findById(ACC_EUR);
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void withdrawOnLackOfFunds_shouldReturnError() {
        when(repo.findById(ACC_EUR)).thenReturn(Optional.of(accountWithBalance(ONE)));

        assertThatThrownBy(() -> sut.withdraw(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency()))
                .isExactlyInstanceOf(WalletService.InsufficientFunds.class);
        verify(repo).findById(ACC_EUR);
        verifyNoMoreInteractions(repo);
    }

    @Test
    public void withdrawConcurrentModification_shouldReturnError() {
        when(repo.findById(ACC_EUR)).thenReturn(Optional.of(accountWithBalance(TEN)));
        when(repo.save(any())).thenThrow(ObjectOptimisticLockingFailureException.class);

        assertThatThrownBy(() -> sut.withdraw(ACC_EUR.getUser(), TEN, ACC_EUR.getCurrency()))
            .isExactlyInstanceOf(WalletService.StaleState.class);
    }

    @Test
    public void balanceExistedAccount_shouldReturnBalancesForEveryCurrency() {
        when(repo.findByAccountIdUser(1)).thenReturn(asList(
                new Account(ACC_EUR, TEN),
                new Account(ACC_USD, ONE)
        ));

        assertThat(sut.getBalance(1)).containsOnly(entry("EUR", TEN), entry("USD", ONE));
    }

    @Test
    public void balanceNotExistedAccount_shouldReturnNothing() {
        when(repo.findByAccountIdUser(1)).thenReturn(emptyList());

        assertThat(sut.getBalance(1)).isEmpty();
    }

    private static Account accountWithBalance(BigDecimal balance) {
        return new Account(ACC_EUR, balance);
    }
}