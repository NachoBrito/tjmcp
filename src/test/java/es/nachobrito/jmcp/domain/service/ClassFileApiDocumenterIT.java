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

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author nacho
 */
@MicronautTest
class ClassFileApiDocumenterIT {

  @Inject ClassDocumenter classDocumenter;

  @Test
  void testDocumentClassFile() {
    var classFilePath =
        "target/classes/es/nachobrito/jmcp/domain/service/ClassFileApiDocumenter.class";
    var path = Path.of("").resolve(classFilePath).toAbsolutePath();

    var report = classDocumenter.document(path);

    assertTrue(report.contains("ClassFileApiDocumenter"));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        // "com/fasterxml/jackson/core/StreamWriteFeature.class",
        // "com/fasterxml/jackson/core/JsonFactory.class",
        "com.fasterxml.jackson.core.util.InternCache"
      })
  void testDocumentFromJarFile(String classFile) throws IOException {
    var jarPath = Path.of("").resolve("test-data/jackson-core-2.14.0.jar").toAbsolutePath();

    var report = classDocumenter.document(classFile, jarPath);
    Files.writeString(Path.of("report-" + System.currentTimeMillis() + ".md"), report);
    assertFalse(report.isBlank());
  }
}
