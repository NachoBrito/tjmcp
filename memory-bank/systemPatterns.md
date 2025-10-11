# System Patterns for JMCP

## Architecture

The JMCP server is built upon the principles of **Hexagonal Architecture (Ports and Adapters)** and **Domain-Driven Design (DDD)**. This ensures a clear separation of concerns and promotes a modular, maintainable, and testable codebase.

### Core Layers

1.  **Domain Layer (`es.nachobrito.jmcp.domain`)**
    *   This is the heart of the application, containing the business logic and domain model.
    *   It has no dependencies on any other layer, ensuring its purity and independence from technical details.
    *   Key components include domain services like `ClassDocumenter`.

2.  **Application Layer (`es.nachobrito.jmcp.application`)**
    *   Contains the primary ports (driving ports) that define how external actors interact with the application.
    *   These ports are implemented by the domain layer.
    *   This layer orchestrates the application's use cases.

3.  **Infrastructure Layer (`es.nachobrito.jmcp.infrastructure`)**
    *   Contains the secondary ports (driven ports) and their adapters, which provide implementations for external systems.
    *   Examples include REST controllers, command-line interfaces, and integrations with third-party libraries like Vineflower for decompilation.
    *   This layer depends on the domain layer.

## Key Design Patterns

-   **Ports and Adapters:**
    *   **Ports:** Interfaces defined in the application and domain layers that represent interaction points.
    *   **Adapters:** Concrete implementations of the ports, located in the infrastructure layer. This allows for swapping out implementations without affecting the core logic (e.g., using a different decompilation tool).

-   **Dependency Inversion Principle:** The domain layer defines interfaces that the infrastructure layer implements. This inverts the traditional flow of dependencies, making the core logic independent of the infrastructure.

-   **Micronaut for Dependency Injection:** The Micronaut framework is used to manage the lifecycle of components and inject dependencies, simplifying the process of wiring together the different layers of the application.
