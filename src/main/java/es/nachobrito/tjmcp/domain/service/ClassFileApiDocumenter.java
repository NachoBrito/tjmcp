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

package es.nachobrito.tjmcp.domain.service;

import es.nachobrito.tjmcp.domain.service.model.ReportModel;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.lang.classfile.*;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nacho
 */
@Singleton
public class ClassFileApiDocumenter implements ClassDocumenter {
  private final Logger log = LoggerFactory.getLogger(getClass());

  private final ReportBuilder reportBuilder;
  private final CodeExplainer codeExplainer;

  public ClassFileApiDocumenter(ReportBuilder reportBuilder, CodeExplainer codeExplainer) {
    this.reportBuilder = reportBuilder;
    this.codeExplainer = codeExplainer;
  }

  @Override
  public String document(Path sourceFile) {
    log.debug("Documenting class file: {}", sourceFile.toString());

    byte[] bytes;
    try {
      bytes = Files.readAllBytes(sourceFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    var classModel = ClassFile.of().parse(bytes);
    return document(classModel);
  }

  @Override
  public String document(String className, Path jarFilePath) {
    log.debug("Documenting class {} from jar file: {}", className, jarFilePath.toString());

    var bytes = JarFileHelper.readClass(className, jarFilePath);
    var classModel = ClassFile.of().parse(bytes);
    return document(classModel);
  }

  @Override
  public String document(ClassModel classModel) {
    var model = ReportModel.with(classModel, codeExplainer);
    return reportBuilder.build(model);
  }
}
