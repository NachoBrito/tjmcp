# Active Context for JMCP

## Current Focus

The primary focus is on the initial setup and implementation of the core functionalities of the JMCP server. This involves establishing the foundational architecture and ensuring that the basic class documentation generation is working correctly.

## Recent Changes

-   Initialized the Memory Bank to establish a baseline for project documentation.
-   Created `projectbrief.md` and `productContext.md`.

## Next Steps

1.  **Implement Core Domain Logic:** Flesh out the `ClassDocumenter` service in the domain layer.
2.  **Develop Infrastructure Layer:** Implement the `VineFlowerClassDocumenter` as the first adapter for decompiling Java classes.
3.  **Build Application Layer:** Create the necessary ports (e.g., REST controllers or CLI commands) to expose the functionality.
4.  **Testing:** Add unit and integration tests to ensure the reliability of the core components.

## Key Decisions & Patterns

-   **DDD and Hexagonal Architecture:** Adhering strictly to these patterns is crucial for maintaining a clean separation of concerns.
-   **Micronaut Framework:** Leveraging Micronaut for dependency injection, configuration, and building the application layer.
