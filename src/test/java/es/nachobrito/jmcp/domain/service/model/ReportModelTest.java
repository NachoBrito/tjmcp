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

package es.nachobrito.jmcp.domain.service.model;

import static org.junit.jupiter.api.Assertions.*;

import es.nachobrito.jmcp.domain.service.JarFileHelper;
import java.lang.classfile.ClassFile;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author nacho
 */
class ReportModelTest {

  @ParameterizedTest
  @ValueSource(
      strings = {
        "com/fasterxml/jackson/core/StreamWriteFeature.class",
        "com/fasterxml/jackson/core/JsonFactory.class",
        "com.fasterxml.jackson.core.util.InternCache"
      })
  void testModelFactoryMethod() {
    var classFile = "com/fasterxml/jackson/core/json/JsonReadContext.class";
    var jarPath = Path.of("").resolve("test-data/jackson-core-2.14.0.jar").toAbsolutePath();
    var bytes = JarFileHelper.readClass(classFile, jarPath);

    var classModel = ClassFile.of().parse(bytes);
    var reportModel = ReportModel.with(classModel, new NullCodeExplainer());

    assertNotNull(reportModel.classDeclaration());
    assertNotNull(reportModel.methods());
    assertNotNull(reportModel.fields());
    assertNotEquals(0, reportModel.methods().length);
    assertNotEquals(0, reportModel.fields().length);

    Arrays.stream(reportModel.methods())
        .forEach(
            method -> {
              assertNotNull(method.name());
              assertFalse(method.name().isEmpty());
              assertFalse(method.name().isBlank());
            });

    Arrays.stream(reportModel.fields())
        .forEach(
            field -> {
              assertNotNull(field.name());
              assertFalse(field.name().isEmpty());
              assertFalse(field.name().isBlank());
            });
  }
}
