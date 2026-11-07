# MS1 addition endpoint: MockMvc test

A Maven/Spring Boot project for Java 17. The endpoint accepts two signed 32-bit integer query parameters, adds them using a 64-bit result to avoid overflow, and returns plain text. The word `Additon` is intentionally spelled as specified in the expected response.

## Import and run in STS4 / Eclipse

1. Extract the ZIP. Use a JDK 17 installation and make sure STS4 uses it (Window > Preferences > Java > Installed JREs).
2. Choose **File > Import > Maven > Existing Maven Projects**. Browse to the extracted folder containing `pom.xml`, select the project, and click Finish. Allow Maven time to download dependencies; an internet connection may be needed.
3. In Package Explorer, open `src/test/java` > `com.example.ms1` > `AdditionControllerTest.java`. Right-click the test class and choose **Run As > JUnit Test**.
4. In the JUnit view, check for a green bar and no failures. The named test sends GET `/ms1/add?a=-5&b=5` and checks HTTP 200 and the exact text `MS 1.1 - Additon = 0`. Additional cases cover zero, positive, negative and integer boundaries.

Alternatively, right-click the project and choose **Run As > Maven test**, or run `mvn test` from the project folder with Maven installed. To start the endpoint, run `Ms1Application` as a Java application or Spring Boot App.

## Viva questions

1. **How do negative edge cases prevent regressions?** They capture intended behavior where signs and cancellation matter. If a later change mishandles negative inputs or changes the response, an automated test fails before the regression reaches users. Boundary tests complement this by detecting overflow and off-by-one errors; they cannot guarantee all inputs are correct.
2. **Which MockMvc assertions check text and JSON?** Use `content().string("expected text")` for an exact plain-text body, often with `content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)`. For JSON, use `content().json("{...}")` to compare JSON content or `jsonPath("$.field").value(...)` to inspect a field, with a JSON content-type assertion where appropriate.
