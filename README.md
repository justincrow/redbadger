# Red Badger Mars Robots

This project simulates a coordinate system and robots operating on Mars.

- **Test Driven:** The application was developed using test-driven principles to help guide my design.
- **Immutable Classes:** Where possible, I used immutability to try to promote safer and more predictable code.
- **No External Frameworks or Libraries:** For this challenge, I decided to KISS, and just use native Kotlin libraries (I would normally use `Result4k` to model results from service calls, as this provides more useful features such as `flatMap`, `peek`, `peekFailure` etc).
- **No Persistence:** The coordinate system and its robots exist only for the duration of a single run; there is no data persistence between executions.

## To Run Tests
To run the tests, you can use the following command:

```bash
./gradlew test
```

## To Run the Application
To run the application, you can use the following command:

```bash
./gradlew runApp
```

## Architecture

This project loosely follows the **ports and adaptors (hexagonal) architecture** pattern. The core domain logic is isolated from external concerns, making the system flexible and maintainable.

- **Domain Package:** Contains the immutable classes and core business logic for the coordinate system and robots.
- **Application Package:** Coordinates interactions between the domain and external interfaces.
- **Adapters Package:** Implements input/output mechanisms, such as CLI or file-based interfaces, connecting the application to the outside world.
- **Test Package:** Provides comprehensive test coverage for domain and application logic, supporting test-driven development.

## Assumptions
Ad - Exception handling is minimal due to time constraints; the application assumes that inputs are valid, although use of `enum`s tries to keep the exceptions in known locations.
 - The ports and adaptors architecture is perhaps slightly overkill for this project, but I wanted to show how the application could be extended in the future.
 - I've used enums to represent the cardinal headings (N, S, E, W) for clarity and type safety, and to allow additional headings such as `NW`, `NE` etc
 - The same enum approach is used for the `Move` and `Turn` commands, so they can be extended in the future if needed.
 - The only handler at present is the `RobotFileInputHandler`, which actually accepts a list of strings (time constraints prevented me from exploring this area more fully).
 - Further handlers could be added to support different input methods, such as CLI or web-based interfaces.

