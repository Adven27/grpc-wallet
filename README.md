# Client-Server gRPC springboot applications

### Server
Dummy Wallet app with deposit\withdraw\balance methods.

Run: `./gradlew :app:bootRun`

### Client
CLI test harness to wallet

Run: `./gradlew :cli:bootRun --args '1 1 1'`

where args:
* number of concurrent users emulated
* number of concurrent requests a user will make
* number of rounds each thread is executing

### ghz-config
[ghz command line benchmarking utility](https://ghz.sh/) configs

Run: `ghz-config> ./ghz --config deposit.json`
