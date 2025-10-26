# An MCP server to show AI coding agents how to use a compiled java class

TJMCP is an MCP server that lets Agents know about compiled Java classes, by using:

- [Java Class File API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/classfile/package-summary.html)
  to extract low level information about the class file.
- [Ollama](https://ollama.com/) for local inference, to interpret the low level details and generate a report of the
  given class.

The result is a Markdown document describing the class, generated locally without having to expose your private code to
a remote LLM.

You can read more about this project in my
article: [An MCP Server To Describe Java Classes](https://www.nachobrito.es/artificial-intelligence/mcp-explain-java-classes/)

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

