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

package es.nachobrito.jmcp.domain.service;

import java.lang.classfile.*;
import java.lang.classfile.attribute.*;

/**
 * @author nacho
 */
public class EnglishMarkdownReportBuilder implements ReportBuilder {
  private final StringBuilder stringBuilder = new StringBuilder();

  private boolean withMainClass = false;
  private boolean withMethodsSection = false;
  private boolean withFieldsSection = false;

  @Override
  public ReportBuilder setMainClass(ClassModel classModel) {
    stringBuilder.append("# ").append(classModel.thisClass().name()).append("\n\n");
    withMainClass = true;
    return this;
  }

  @Override
  public ReportBuilder addMethod(MethodModel methodModel) {
    if (!withMethodsSection) {
      stringBuilder.append("\n## Methods").append("\n\n");
      withMethodsSection = true;
    }
    stringBuilder.append("### ").append(methodModel.methodName()).append("\n");
    return this;
  }

  @Override
  public ReportBuilder addField(FieldModel fieldModel) {
    if (!withFieldsSection) {
      stringBuilder.append("\n## Fields").append("\n\n");
      withFieldsSection = true;
    }
    stringBuilder.append("### ").append(fieldModel.fieldName()).append("\n");
    return this;
  }

  @Override
  public String build() {
    if (!withMainClass) {
      throw new IllegalStateException("Cannot generate report without definning main class!");
    }
    return stringBuilder.toString();
  }

  @Override
  public ReportBuilder addAccessFlags(AccessFlags accessFlags) {
    return this;
  }

  @Override
  public ReportBuilder addClassFileVersion(ClassFileVersion classFileVersion) {
    return this;
  }

  @Override
  public <T extends CustomAttribute<T>> ReportBuilder addCustomAttribute(
      CustomAttribute<T> customAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addInterfaces(Interfaces interfaces) {
    return this;
  }

  @Override
  public ReportBuilder addSuperclass(Superclass superclass) {
    return this;
  }

  @Override
  public ReportBuilder addCompilationIDAttribute(CompilationIDAttribute compilationIDAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addDeprecatedAttribute(DeprecatedAttribute deprecatedAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addEnclosingMethodAttribute(
      EnclosingMethodAttribute enclosingMethodAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addInnerClassesAttribute(InnerClassesAttribute innerClassesAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModuleAttribute(ModuleAttribute moduleAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModuleHashesAttribute(ModuleHashesAttribute moduleHashesAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModuleMainClassAttribute(
      ModuleMainClassAttribute moduleMainClassAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModulePackagesAttribute(ModulePackagesAttribute modulePackagesAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModuleResolutionAttribute(
      ModuleResolutionAttribute moduleResolutionAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addModuleTargetAttribute(ModuleTargetAttribute moduleTargetAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addNestHostAttribute(NestHostAttribute nestHostAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addNestMembersAttribute(NestMembersAttribute nestMembersAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addPermittedSubclassesAttribute(
      PermittedSubclassesAttribute permittedSubclassesAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addRecordAttribute(RecordAttribute recordAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addRuntimeInvisibleAnnotationsAttribute(
      RuntimeInvisibleAnnotationsAttribute runtimeInvisibleAnnotationsAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addRuntimeInvisibleTypeAnnotationsAttribute(
      RuntimeInvisibleTypeAnnotationsAttribute runtimeInvisibleTypeAnnotationsAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addRuntimeVisibleAnnotationsAttribute(
      RuntimeVisibleAnnotationsAttribute runtimeVisibleAnnotationsAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addRuntimeVisibleTypeAnnotationsAttribute(
      RuntimeVisibleTypeAnnotationsAttribute runtimeVisibleTypeAnnotationsAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addSignatureAttribute(SignatureAttribute signatureAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addSourceDebugExtensionAttribute(
      SourceDebugExtensionAttribute sourceDebugExtensionAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addSourceFileAttribute(SourceFileAttribute sourceFileAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addSourceIDAttribute(SourceIDAttribute sourceIDAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addSyntheticAttribute(SyntheticAttribute syntheticAttribute) {
    return this;
  }

  @Override
  public ReportBuilder addUnknownAttribute(UnknownAttribute unknownAttribute) {
    return this;
  }
}
