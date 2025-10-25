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

package es.nachobrito.tjmcp.domain.service.model;

import java.lang.classfile.constantpool.ClassEntry;
import java.lang.constant.ClassDesc;

/**
 * @author nacho
 */
public class ClassNameHelper {
  static String getFullName(ClassEntry classEntry) {
    return getFullName(classEntry.asSymbol());
  }

  public static String getFullName(ClassDesc classDesc) {
    var packageName = classDesc.packageName();
    var suffix = classDesc.isArray() ? "[]" : "";
    if (packageName != null && !packageName.isEmpty()) {
      return classDesc.packageName() + "." + classDesc.displayName() + suffix;
    }
    return classDesc.displayName() + suffix;
  }
}
