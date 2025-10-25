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

import java.lang.classfile.ClassModel;
import java.lang.reflect.AccessFlag;
import java.util.stream.Collectors;

/**
 * @author nacho
 */
public record ClassDeclaration(
    String flags,
    Type type,
    String packageName,
    String name,
    String interfaces,
    String superClass) {

  public static ClassDeclaration of(ClassModel classModel) {
    var flags =
        classModel.flags().flags().stream()
            .filter(AccessFlag::sourceModifier)
            .map(AccessFlag::name)
            .map(String::toLowerCase)
            .collect(Collectors.joining(" "));
    var type = Type.of(classModel);
    var name = classModel.thisClass().asSymbol().displayName();
    var packageName = classModel.thisClass().asSymbol().packageName();
    var interfaces =
        classModel.interfaces().stream()
            .map(ClassNameHelper::getFullName)
            .collect(Collectors.joining(", "));
    var superClass = classModel.superclass().map(ClassNameHelper::getFullName).orElse(null);

    return new ClassDeclaration(flags, type, packageName, name, interfaces, superClass);
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    if (packageName != null) {
      builder.append("package ").append(packageName).append(";\n\n");
    }
    builder.append(flags).append(" ").append(type.name).append(" ").append(name);
    if (superClass != null) {
      builder.append("\nextends ").append(superClass);
    }
    if (interfaces != null) {
      builder.append("\nimplements ").append(interfaces);
    }
    return builder.toString();
  }

  public String fullName() {
    return packageName + "." + name;
  }
}
