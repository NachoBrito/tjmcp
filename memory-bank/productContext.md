# Product Context for JMCP

## Core Purpose

JMCP (Java MCP Server) is designed to provide AI Agents with a robust tool for investigating and understanding Java classes. The primary goal is to bridge the gap between AI-driven development and existing Java codebases, enabling agents to analyze, document, and interact with Java projects more effectively.

## Problem Solved

AI Agents often lack the context and tools to deeply analyze compiled Java code (e.g., `.class` files). JMCP addresses this by providing a clear, structured way to obtain documentation and insights about Java classes, effectively "decompiling" them into a human-readable and agent-understandable format.

## How it Works

JMCP functions as a server that exposes endpoints for class analysis. It can be accessed in two primary ways:

1.  **REST Service:** Agents can make HTTP requests to the server to get information about specific Java classes.
2.  **Command-Line Utility:** Developers or agents can use it directly from the terminal for quick analysis or integration into scripts.

## User Experience Goals

-   **Simplicity:** The API and CLI should be straightforward and easy to use.
-   **Accuracy:** The generated documentation must accurately reflect the structure and content of the Java classes.
-   **Extensibility:** The server should be designed to allow for future enhancements, such as supporting more complex analysis or different output formats.
