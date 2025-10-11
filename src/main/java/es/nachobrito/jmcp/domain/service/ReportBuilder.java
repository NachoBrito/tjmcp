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
public interface ReportBuilder {

  static ReportBuilder getDefault() {
    return new EnglishMarkdownReportBuilder();
  }

  ReportBuilder setMainClass(ClassModel classModel);

  ReportBuilder addMethod(MethodModel methodModel);

  ReportBuilder addField(FieldModel fieldModel);

  String build();

  ReportBuilder addAccessFlags(AccessFlags accessFlags);

  ReportBuilder addClassFileVersion(ClassFileVersion classFileVersion);

  <T extends CustomAttribute<T>> ReportBuilder addCustomAttribute(
      CustomAttribute<T> customAttribute);

  ReportBuilder addInterfaces(Interfaces interfaces);

  ReportBuilder addSuperclass(Superclass superclass);

  ReportBuilder addCompilationIDAttribute(CompilationIDAttribute compilationIDAttribute);

  ReportBuilder addDeprecatedAttribute(DeprecatedAttribute deprecatedAttribute);

  ReportBuilder addEnclosingMethodAttribute(EnclosingMethodAttribute enclosingMethodAttribute);

  ReportBuilder addInnerClassesAttribute(InnerClassesAttribute innerClassesAttribute);

  ReportBuilder addModuleAttribute(ModuleAttribute moduleAttribute);

  ReportBuilder addModuleHashesAttribute(ModuleHashesAttribute moduleHashesAttribute);

  ReportBuilder addModuleMainClassAttribute(ModuleMainClassAttribute moduleMainClassAttribute);

  ReportBuilder addModulePackagesAttribute(ModulePackagesAttribute modulePackagesAttribute);

  ReportBuilder addModuleResolutionAttribute(ModuleResolutionAttribute moduleResolutionAttribute);

  ReportBuilder addModuleTargetAttribute(ModuleTargetAttribute moduleTargetAttribute);

  ReportBuilder addNestHostAttribute(NestHostAttribute nestHostAttribute);

  ReportBuilder addNestMembersAttribute(NestMembersAttribute nestMembersAttribute);

  ReportBuilder addPermittedSubclassesAttribute(
      PermittedSubclassesAttribute permittedSubclassesAttribute);

  ReportBuilder addRecordAttribute(RecordAttribute recordAttribute);

  ReportBuilder addRuntimeInvisibleAnnotationsAttribute(
      RuntimeInvisibleAnnotationsAttribute runtimeInvisibleAnnotationsAttribute);

  ReportBuilder addRuntimeInvisibleTypeAnnotationsAttribute(
      RuntimeInvisibleTypeAnnotationsAttribute runtimeInvisibleTypeAnnotationsAttribute);

  ReportBuilder addRuntimeVisibleAnnotationsAttribute(
      RuntimeVisibleAnnotationsAttribute runtimeVisibleAnnotationsAttribute);

  ReportBuilder addRuntimeVisibleTypeAnnotationsAttribute(
      RuntimeVisibleTypeAnnotationsAttribute runtimeVisibleTypeAnnotationsAttribute);

  ReportBuilder addSignatureAttribute(SignatureAttribute signatureAttribute);

  ReportBuilder addSourceDebugExtensionAttribute(
      SourceDebugExtensionAttribute sourceDebugExtensionAttribute);

  ReportBuilder addSourceFileAttribute(SourceFileAttribute sourceFileAttribute);

  ReportBuilder addSourceIDAttribute(SourceIDAttribute sourceIDAttribute);

  ReportBuilder addSyntheticAttribute(SyntheticAttribute syntheticAttribute);

  ReportBuilder addUnknownAttribute(UnknownAttribute unknownAttribute);
}
