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

package es.nachobrito.tjmcp.infrastructure.micronaut;

import static org.junit.jupiter.api.Assertions.*;

import es.nachobrito.tjmcp.domain.service.ClassDocumenter;
import es.nachobrito.tjmcp.domain.service.CodeExplainer;
import es.nachobrito.tjmcp.domain.service.model.NullCodeExplainer;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * @author nacho
 */
@MicronautTest(rebuildContext = true)
@Property(name = "tjmcp.cache-folder", value = CachingClassDocumenterListenerTest.TEST_CACHE)
class CachingClassDocumenterListenerTest {

  public static final String TEST_CACHE = "./test-cache";

  @MockBean(CodeExplainer.class)
  CodeExplainer codeExplainer = new NullCodeExplainer();

  @Inject ClassDocumenter classDocumenter;

  @Test
  void testCachingDocumenter() throws IOException {
    var jarPath = Path.of("").resolve("test-data/jackson-core-2.14.0.jar").toAbsolutePath();

    var className = "com.fasterxml.jackson.core.JsonFactory";
    var cacheFile = Path.of(TEST_CACHE, "com/fasterxml/jackson/core/JsonFactory.md");
    assertFalse(cacheFile.toFile().isFile());
    var report = classDocumenter.document(className, jarPath);

    assertTrue(cacheFile.toFile().isFile());
    assertEquals(Files.readString(cacheFile), report);
  }

  @AfterEach
  void cleanup() throws IOException {
    var cacheFolder = Path.of(TEST_CACHE);
    if (cacheFolder.toFile().isDirectory()) {
      try (Stream<Path> paths = Files.walk(cacheFolder)) {
        paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
      }
    }
  }
}
