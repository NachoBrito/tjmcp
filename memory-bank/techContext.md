# Technical Context for JMCP

## Core Technologies

-   **Java:** The primary programming language for the project.
-   **Maven:** Used for dependency management and building the project.
-   **Micronaut:** A modern, JVM-based framework for building modular, easily testable microservices and serverless applications. It's used for dependency injection, AOP, and building the REST API.

## Key Libraries and Tools

-   **Vineflower:** A Java decompiler (formerly Fernflower) used to convert `.class` files back into human-readable Java source code. This is a critical component of the infrastructure layer.
-   **JUnit 5:** The testing framework used for writing unit and integration tests.
-   **Mockito:** A mocking framework for isolating components during testing.

## Development Environment

-   **IDE:** Any modern Java IDE (like IntelliJ IDEA or VS Code with Java extensions) can be used.
-   **JDK:** A recent version of the Java Development Kit is required (e.g., JDK 17 or later).

## Technical Constraints

-   **JVM-Based:** The project is tied to the Java Virtual Machine, meaning it can run on any platform that supports a compatible JVM.
-   **Dependency on Decompiler:** The accuracy and quality of the output are directly dependent on the capabilities of the underlying decompiler (Vineflower).
