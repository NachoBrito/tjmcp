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

import java.io.File;
import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nacho
 */
public class CachingClassDocumenter implements ClassDocumenter {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private final Path cacheFolder;
  private final ClassDocumenter delegate;

  public CachingClassDocumenter(Path cacheFolderPath, ClassDocumenter delegate) {
    cacheFolder = cacheFolderPath;
    this.delegate = delegate;
    log.info("Caching to folder {} enabled", cacheFolder.toAbsolutePath());
  }

  @Override
  public String document(Path sourceFile) {
    byte[] bytes;
    try {
      bytes = Files.readAllBytes(sourceFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    var classModel = ClassFile.of().parse(bytes);
    return getCachedReport(classModel).orElseGet(() -> documentAndCache(classModel));
  }

  @Override
  public String document(String className, Path jarFilePath) {
    var bytes = JarFileHelper.readClass(className, jarFilePath);
    var classModel = ClassFile.of().parse(bytes);
    return getCachedReport(classModel).orElse(documentAndCache(classModel));
  }

  @Override
  public String document(ClassModel classModel) {
    return getCachedReport(classModel).orElse(documentAndCache(classModel));
  }

  Optional<String> getCachedReport(ClassModel classModel) {
    var cacheFile = getCacheFile(classModel);
    if (cacheFile.toFile().isFile()) {
      try {
        log.info("Returning cached report: {}", cacheFile.toAbsolutePath());
        var report = Files.readString(cacheFile);
        return Optional.of(report);
      } catch (IOException e) {
        log.error("Cannot read cached file {}: {}", cacheFile, e.getMessage(), e);
        return Optional.empty();
      }
    }
    return Optional.empty();
  }

  private Path getCacheFile(ClassModel classModel) {
    var symbol = classModel.thisClass().asSymbol();
    var packageName = symbol.packageName();
    var className = symbol.displayName();

    var folderPath = packageName.replace('.', File.separatorChar);
    var folder = Path.of(this.cacheFolder.toAbsolutePath().toString(), folderPath);
    if (!folder.toFile().isDirectory()) {
      if (!folder.toFile().mkdirs()) {
        throw new IllegalStateException(
            "Could not create destination folder: %s".formatted(folder.toAbsolutePath()));
      }
    }
    return Path.of(folder.toAbsolutePath().toString(), className + ".md");
  }

  private String documentAndCache(ClassModel classModel) {
    var report = delegate.document(classModel);
    var cacheFile = getCacheFile(classModel);
    log.info("Generating report: {}", cacheFile);
    try {
      Files.writeString(cacheFile, report);
    } catch (IOException e) {
      log.error("Could not save class report to file {}", cacheFile.toAbsolutePath(), e);
      throw new IllegalStateException(e);
    }
    return report;
  }
}
