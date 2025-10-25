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

import java.lang.classfile.ClassModel;

/**
 * @author nacho
 */
public enum Type {
  CLASS("class"),
  INTERFACE("interface"),
  ENUM("enum"),
  MODULE_INFO("module-info");

  final String name;

  Type(String name) {
    this.name = name;
  }

  // todo: implement logic to identify interfaces and enums
  static Type of(ClassModel model) {
    if (model.isModuleInfo()) {
      return MODULE_INFO;
    }
    return CLASS;
  }
}
