# An MCP server to show AI coding agents how to use a compiled java class

## How to use it

1. Build the project:
    ```shell
    mvn clean install
    ```
2. Add the following configuration to your agent:
    
    ```json
    {
      "tjmcp": {
        "command": "/path/to/java",
        "args": [
          "-jar",
          "/path/to/project/target/tjmcp-0.1.jar"
        ],
        "env": {
          "TJMCP_CACHE_FOLDER": "/home/nacho/tjmcp-cache",
          "TJMCP_LANGCHAIN4J_OLLAMA_URL": "http://localhost:11434",
          "TJMCP_LANGCHAIN4J_OLLAMA_MODEL": "qwen3-coder:latest"
        },
        "timeout": 300
      }
    }
    ```

## Micronaut 4.10.7 Documentation

- [User Guide](https://docs.micronaut.io/4.10.7/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.10.7/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.10.7/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

