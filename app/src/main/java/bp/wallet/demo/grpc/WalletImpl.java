package bp.wallet.demo.grpc;

import bp.wallet.demo.*;
import bp.wallet.demo.bl.WalletService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
class WalletImpl extends WalletGrpc.WalletImplBase {
    private final WalletService service;

    WalletImpl(WalletService service) {
        this.service = service;
    }

    @Override
    public void balance(BalanceRequest req, StreamObserver<BalanceResponse> responseObserver) {
        responseObserver.onNext(
                BalanceResponse.newBuilder()
                        .putAllBalances(
                                service.getBalance(parseInt(req.getUser())).entrySet().stream()
                                        .collect(toMap(Map.Entry::getKey, e -> e.getValue().doubleValue()))
                        ).build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void deposit(WalletRequest req, StreamObserver<WalletResponse> responseObserver) {
        responseObserver.onNext(response(depositResult(req)));
        responseObserver.onCompleted();
    }

    private String depositResult(WalletRequest req) {
        try {
            service.deposit(parseInt(req.getUser()), toBigDecimal(req.getAmount()), req.getCurrency());
        } catch (WalletService.NegativeAmount e) {
            return "negative_amount";
        } catch (ObjectOptimisticLockingFailureException | DataIntegrityViolationException e) {
            return "stale_state";
        }
        return "ok";
    }

    @Override
    public void withdraw(WalletRequest req, StreamObserver<WalletResponse> responseObserver) {
        responseObserver.onNext(response(withdrawResult(req)));
        responseObserver.onCompleted();
    }

    private String withdrawResult(WalletRequest req) {
        try {
            service.withdraw(parseInt(req.getUser()), toBigDecimal(req.getAmount()), req.getCurrency());
        } catch (WalletService.InsufficientFunds e) {
            return "insufficient_funds";
        } catch (WalletService.NegativeAmount e) {
            return "negative_amount";
        } catch (ObjectOptimisticLockingFailureException | DataIntegrityViolationException e) {
            return "stale_state";
        }
        return "ok";
    }

    @Override
    public StreamObserver<WalletRequest> withdrawS(StreamObserver<WalletResponse> responseObserver) {
        return new StreamObserver<WalletRequest>() {

            @Override
            public void onNext(WalletRequest req) {
                service.withdraw(parseInt(req.getUser()), toBigDecimal(req.getAmount()), req.getCurrency());
            }

            @Override
            public void onError(Throwable t) {
                log.warn("Encountered error in withdrawS", t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(response("ok"));
                responseObserver.onCompleted();
            }
        };
    }

    private WalletResponse response(String message) {
        return WalletResponse.newBuilder().setMessage(message).build();
    }

    private BigDecimal toBigDecimal(double amount) {
        return BigDecimal.valueOf(amount);
    }
}
