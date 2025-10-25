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

import es.nachobrito.tjmcp.domain.service.CodeExplainer;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;
import java.lang.classfile.attribute.ExceptionsAttribute;
import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nacho
 */
public record Method(
    String flags,
    String returnType,
    String name,
    String[] arguments,
    String[] exceptions,
    String explanation) {
  public static Method[] allOf(ClassModel classModel, CodeExplainer codeExplainer) {
    return classModel.methods().stream()
        .map(it -> of(it, codeExplainer))
        .toList()
        .toArray(new Method[0]);
  }

  private static Method of(MethodModel methodModel, CodeExplainer codeExplainer) {
    var flags =
        methodModel.flags().flags().stream()
            .filter(AccessFlag::sourceModifier)
            .map(AccessFlag::name)
            .map(String::toLowerCase)
            .collect(Collectors.joining(" "));
    var name = methodModel.methodName().stringValue();
    var returnType = ClassNameHelper.getFullName(methodModel.methodTypeSymbol().returnType());
    var paramNames = new HashMap<String, Integer>();
    var arguments =
        methodModel.methodTypeSymbol().parameterList().stream()
            .map(it -> toParamName(it, paramNames))
            .distinct()
            .toArray(String[]::new);
    var exceptions =
        methodModel.attributes().stream()
            .filter(it -> it instanceof ExceptionsAttribute)
            .findFirst()
            .map(it -> ((ExceptionsAttribute) it).exceptions())
            .orElse(Collections.emptyList())
            .stream()
            .map(ClassNameHelper::getFullName)
            .toArray(String[]::new);

    var explanation = methodModel.code().map(codeExplainer::explainCode).orElse("");

    return new Method(flags, returnType, name, arguments, exceptions, explanation);
  }

  private static String toParamName(ClassDesc classDesc, Map<String, Integer> paramNames) {
    var displayName = classDesc.displayName();
    var paramName = displayName.substring(0, 1).toLowerCase() + displayName.substring(1);
    if (classDesc.isPrimitive()) {
      paramName += "Value";
    }
    int count = paramNames.computeIfAbsent(paramName, (k) -> 0);
    paramNames.put(paramName, count + 1);
    return ClassNameHelper.getFullName(classDesc)
        + " "
        + paramName
        + (count > 0 ? "%d".formatted(count) : "");
  }

  private String argumentsString() {
    if (arguments == null || arguments.length == 0) {
      return "";
    }
    return String.join(", ", arguments);
  }

  private String exceptionsString() {
    if (exceptions == null || exceptions.length == 0) {
      return "";
    }
    return " throws " + String.join(", ", exceptions);
  }

  public boolean hasExplanation() {
    return explanation != null && !explanation.isBlank();
  }

  @Override
  public String toString() {
    return flags
        + " "
        + returnType
        + " "
        + name
        + "("
        + argumentsString()
        + ")"
        + exceptionsString();
  }
}
