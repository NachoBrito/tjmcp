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

import java.lang.classfile.ClassModel;
import java.nio.file.Path;

/**
 * @author nacho
 */
public interface ClassDocumenter {
  /**
   * Documents the class defined in the provide .class file
   *
   * @param classFile the source path
   * @return text documenting the class
   */
  String document(Path classFile);

  /**
   * Documents the class with given class name that is found inside the jar file.
   *
   * @param className the name of the class to document
   * @param jarFile the path to the jar file
   * @return the text documenting the class
   */
  String document(String className, Path jarFile);

  /**
   * Documents the class represented by the given ClassModel object
   *
   * @param classModel the class model
   * @return the text documenting the class
   */
  String document(ClassModel classModel);
}
