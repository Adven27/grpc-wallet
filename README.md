# Client-Server gRPC springboot applications

### Server
Dummy Wallet app with deposit\withdraw\balance methods.

Prerequisites: docker

Run: `./gradlew :app:bootRun`

### Client
CLI test harness to wallet

Run: `./gradlew :cli:bootRun --args '1 1 1'`

where args:
* number of concurrent users emulated
* number of concurrent requests a user will make
* number of rounds each thread is executing

### Solution side note
Assuming that concurrent access to the account is not common case, server relies on the optimistic locking for solving this problem:
`stale_state` message will be returned to a client. 

It's up to client how to deal with it. CLI-harness in this project will retry operations on `stale_state` result.

### Performance testing
[ghz command line benchmarking utility](https://ghz.sh/) was used for performance testing.
Example configs may be found in `ghz-config` folder.

Run: `ghz-config> ./ghz --config deposit.json`

Results on dev machine:

* balance request ~2000 RPS
* deposit\withdraw request ~700 RPS  