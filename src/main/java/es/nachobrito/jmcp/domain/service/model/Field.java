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
import java.lang.classfile.FieldModel;
import java.lang.reflect.AccessFlag;
import java.util.stream.Collectors;

/**
 * @author nacho
 */
public record Field(String flags, String type, String name) {
  public static Field[] allOf(ClassModel classModel) {
    return classModel.fields().stream().map(Field::of).toList().toArray(new Field[0]);
  }

  private static Field of(FieldModel fieldModel) {
    var flags =
        fieldModel.flags().flags().stream()
            .filter(AccessFlag::sourceModifier)
            .map(AccessFlag::name)
            .map(String::toLowerCase)
            .collect(Collectors.joining(" "));
    var type = ClassNameHelper.getFullName(fieldModel.fieldTypeSymbol());
    var name = fieldModel.fieldName().stringValue();
    return new Field(flags, type, name);
  }

  @Override
  public String toString() {
    var builder = new StringBuilder();
    if (flags != null) {
      builder.append(flags).append(" ");
    }
    builder.append(type).append(" ").append(name);
    return builder.toString();
  }
}
