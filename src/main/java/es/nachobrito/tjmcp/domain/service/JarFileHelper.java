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

import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author nacho
 */
public class JarFileHelper {
  public static byte[] readClass(String className, Path jarFilePath) {
    if (className.contains(".") && !className.contains("/")) {
      className = className.replace('.', '/');
    }
    if (!className.contains(".class")) {
      className = className + ".class";
    }
    try (var jarFile = new JarFile(jarFilePath.toFile())) {
      // var names = jarFile.stream().map(ZipEntry::getName).collect(Collectors.toSet());
      var entry = jarFile.getEntry(className);
      if (entry == null) {
        throw new IllegalArgumentException(
            "Class entry '%s' not found in '%s'".formatted(className, jarFilePath.toString()));
      }
      return readBytes(jarFile, entry);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static byte[] readBytes(JarFile jarFile, ZipEntry entry) {
    try (var inputStream = jarFile.getInputStream(entry)) {
      return inputStream.readAllBytes();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
