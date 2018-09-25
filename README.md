## AMQ connection tester

This is a small sample to create a given number of connections to a AMQ Broker in order to test connection limits. Use it as reference for your own implementation.

### Configuration

1. Edit the `src/main/resources/jndi.properties` file and add your broker credentials to it.

2. Edit the `CreateConnections.java` in line `15` to set the number of connections you want to create to the broker.

3. Run the `CreateConnections.java` to create the connections.
