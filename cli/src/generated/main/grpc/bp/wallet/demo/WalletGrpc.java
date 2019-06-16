package bp.wallet.demo;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: wallet.proto")
public final class WalletGrpc {

  private WalletGrpc() {}

  public static final String SERVICE_NAME = "wallet.Wallet";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest,
      bp.wallet.demo.WalletResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = bp.wallet.demo.WalletRequest.class,
      responseType = bp.wallet.demo.WalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest,
      bp.wallet.demo.WalletResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest, bp.wallet.demo.WalletResponse> getDepositMethod;
    if ((getDepositMethod = WalletGrpc.getDepositMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getDepositMethod = WalletGrpc.getDepositMethod) == null) {
          WalletGrpc.getDepositMethod = getDepositMethod = 
              io.grpc.MethodDescriptor.<bp.wallet.demo.WalletRequest, bp.wallet.demo.WalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.Wallet", "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.WalletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.WalletResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("Deposit"))
                  .build();
          }
        }
     }
     return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest,
      bp.wallet.demo.WalletResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = bp.wallet.demo.WalletRequest.class,
      responseType = bp.wallet.demo.WalletResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest,
      bp.wallet.demo.WalletResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<bp.wallet.demo.WalletRequest, bp.wallet.demo.WalletResponse> getWithdrawMethod;
    if ((getWithdrawMethod = WalletGrpc.getWithdrawMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getWithdrawMethod = WalletGrpc.getWithdrawMethod) == null) {
          WalletGrpc.getWithdrawMethod = getWithdrawMethod = 
              io.grpc.MethodDescriptor.<bp.wallet.demo.WalletRequest, bp.wallet.demo.WalletResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.Wallet", "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.WalletRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.WalletResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("Withdraw"))
                  .build();
          }
        }
     }
     return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<bp.wallet.demo.BalanceRequest,
      bp.wallet.demo.BalanceResponse> getBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Balance",
      requestType = bp.wallet.demo.BalanceRequest.class,
      responseType = bp.wallet.demo.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<bp.wallet.demo.BalanceRequest,
      bp.wallet.demo.BalanceResponse> getBalanceMethod() {
    io.grpc.MethodDescriptor<bp.wallet.demo.BalanceRequest, bp.wallet.demo.BalanceResponse> getBalanceMethod;
    if ((getBalanceMethod = WalletGrpc.getBalanceMethod) == null) {
      synchronized (WalletGrpc.class) {
        if ((getBalanceMethod = WalletGrpc.getBalanceMethod) == null) {
          WalletGrpc.getBalanceMethod = getBalanceMethod = 
              io.grpc.MethodDescriptor.<bp.wallet.demo.BalanceRequest, bp.wallet.demo.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "wallet.Wallet", "Balance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  bp.wallet.demo.BalanceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new WalletMethodDescriptorSupplier("Balance"))
                  .build();
          }
        }
     }
     return getBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletStub newStub(io.grpc.Channel channel) {
    return new WalletStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WalletBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WalletFutureStub(channel);
  }

  /**
   */
  public static abstract class WalletImplBase implements io.grpc.BindableService {

    /**
     */
    public void deposit(bp.wallet.demo.WalletRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     */
    public void withdraw(bp.wallet.demo.WalletRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void balance(bp.wallet.demo.BalanceRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bp.wallet.demo.WalletRequest,
                bp.wallet.demo.WalletResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bp.wallet.demo.WalletRequest,
                bp.wallet.demo.WalletResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                bp.wallet.demo.BalanceRequest,
                bp.wallet.demo.BalanceResponse>(
                  this, METHODID_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class WalletStub extends io.grpc.stub.AbstractStub<WalletStub> {
    private WalletStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletStub(channel, callOptions);
    }

    /**
     */
    public void deposit(bp.wallet.demo.WalletRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdraw(bp.wallet.demo.WalletRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void balance(bp.wallet.demo.BalanceRequest request,
        io.grpc.stub.StreamObserver<bp.wallet.demo.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WalletBlockingStub extends io.grpc.stub.AbstractStub<WalletBlockingStub> {
    private WalletBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletBlockingStub(channel, callOptions);
    }

    /**
     */
    public bp.wallet.demo.WalletResponse deposit(bp.wallet.demo.WalletRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public bp.wallet.demo.WalletResponse withdraw(bp.wallet.demo.WalletRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public bp.wallet.demo.BalanceResponse balance(bp.wallet.demo.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WalletFutureStub extends io.grpc.stub.AbstractStub<WalletFutureStub> {
    private WalletFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<bp.wallet.demo.WalletResponse> deposit(
        bp.wallet.demo.WalletRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<bp.wallet.demo.WalletResponse> withdraw(
        bp.wallet.demo.WalletRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<bp.wallet.demo.BalanceResponse> balance(
        bp.wallet.demo.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_BALANCE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WalletImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WalletImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT:
          serviceImpl.deposit((bp.wallet.demo.WalletRequest) request,
              (io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((bp.wallet.demo.WalletRequest) request,
              (io.grpc.stub.StreamObserver<bp.wallet.demo.WalletResponse>) responseObserver);
          break;
        case METHODID_BALANCE:
          serviceImpl.balance((bp.wallet.demo.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<bp.wallet.demo.BalanceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return bp.wallet.demo.WalletProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Wallet");
    }
  }

  private static final class WalletFileDescriptorSupplier
      extends WalletBaseDescriptorSupplier {
    WalletFileDescriptorSupplier() {}
  }

  private static final class WalletMethodDescriptorSupplier
      extends WalletBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WalletMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WalletGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletFileDescriptorSupplier())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
