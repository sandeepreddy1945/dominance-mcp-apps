## Mock Server for Local testing the MCP End points

Start the app in mock profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mock
```

Up the mock server using docker compose:

```bash
docker compose up
```

To run in watch mode in order to reload the files in real time: 

```bash
docker compose watch
```