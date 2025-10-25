/*
 *    Copyright 2025 Nacho Brito
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package es.nachobrito.tjmcp.infrastructure.micronaut.mcp;

import es.nachobrito.tjmcp.domain.service.ClassDocumenter;
import io.micronaut.mcp.annotations.Tool;
import io.micronaut.mcp.annotations.ToolArg;
import jakarta.inject.Singleton;
import java.nio.file.Path;

/**
 * @author nacho
 */
@Singleton
public class DocumentClassFileTool {
  private final ClassDocumenter classDocumenter;

  public DocumentClassFileTool(ClassDocumenter classDocumenter) {
    this.classDocumenter = classDocumenter;
  }

  @Tool(
      title = "Document a class file",
      description =
          "Returns a MarkDown report describing the class defined in the provided class file.",
      annotations =
          @Tool.ToolAnnotations(
              readOnlyHint = true,
              destructiveHint = false,
              idempotentHint = true,
              openWorldHint = false,
              returnDirect = true))
  String documentClassFile(
      @ToolArg(description = "The absolute path of the class file") String classFilePath) {
    var path = Path.of(classFilePath);
    if (!path.toFile().isFile()) {
      throw new IllegalArgumentException("Invalid path: %s".formatted(classFilePath));
    }
    return classDocumenter.document(path);
  }

  @Tool(
      title = "Document a class inside a Jar file",
      description =
          "Returns a MarkDown report describing the class with the given name, found inside a Jar file.",
      annotations =
          @Tool.ToolAnnotations(
              readOnlyHint = true,
              destructiveHint = false,
              idempotentHint = true,
              openWorldHint = false,
              returnDirect = true))
  String documentClassInJar(
      @ToolArg(description = "The full class name") String className,
      @ToolArg(description = "The absolute path of the jar file") String jarFilePath) {
    var path = Path.of(jarFilePath);
    if (!path.toFile().isFile()) {
      throw new IllegalArgumentException("Invalid path: %s".formatted(jarFilePath));
    }
    if (className == null || className.isEmpty()) {
      throw new IllegalArgumentException("className cannot be empty");
    }
    return classDocumenter.document(className, path);
  }
}
