# JMCP, an MCP server to document Java Classes

JMCP is a project to build an MCP server that AI Agents can use to investigate about Java Classes. It can work both as a command-line utility and a REST service.

The following is a high-level list description of the applied principles:

- We use Micronaut as application framework.
- Internally the project follows Domain Driven Design (DDD) and Hexagonal Architecture (Ports and Adapters)
- Code is organized in the following packages:
    - `es.nachobrito.jmcp.domain` contains the application core, implementing the business logic and domain model.
    - `es.nachobrito.jmcp.application` contains the primary ports (driving ports), implemented by the core and used by external actors to interact with the application
    - `es.nachobrito.jmcp.infrastructure` contains the secondary ports (driven ports), implemented by external systems and used by the core to interact with them, such as repositories for data access.

- The application core does not depend on any other layer.
- Both the application and infrastructure layer depend on the core, and external actors.