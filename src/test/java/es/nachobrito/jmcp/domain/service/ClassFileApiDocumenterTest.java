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

package es.nachobrito.jmcp.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author nacho
 */
class ClassFileApiDocumenterTest {

  @Test
  void testDocumentClassFile() {
    var classFilePath =
        "target/classes/es/nachobrito/jmcp/domain/service/ClassFileApiDocumenter.class";
    var path = Path.of("").resolve(classFilePath).toAbsolutePath();

    var documenter = new ClassFileApiDocumenter();
    var report = documenter.document(path);

    assertTrue(report.contains("ClassFileApiDocumenter"));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "com/fasterxml/jackson/core/JsonFactory.class",
        "com/fasterxml/jackson/core/JsonFactory",
        "com.fasterxml.jackson.core.JsonFactory",
      })
  void testDocumentFromJarFile(String classFile) {
    var jarPath = Path.of("").resolve("test-data/jackson-core-2.14.0.jar").toAbsolutePath();

    var documenter = new ClassFileApiDocumenter();
    var report = documenter.document(classFile, jarPath);
    assertTrue(report.contains("JsonFactory"));
  }
}
