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

import java.io.IOException;
import java.lang.classfile.*;
import java.lang.classfile.attribute.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author nacho
 */
public class ClassFileApiDocumenter implements ClassDocumenter {
  @Override
  public String document(Path sourceFile) {
    byte[] bytes;
    try {
      bytes = Files.readAllBytes(sourceFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    var classModel = ClassFile.of().parse(bytes);
    return document(classModel);
  }

  @Override
  public String document(String className, Path jarFilePath) {
      var bytes = JarFileHelper.readClass(className, jarFilePath);
      var classModel = ClassFile.of().parse(bytes);
      return document(classModel);
  }

  private static String document(ClassModel classModel) {
    var builder = ReportBuilder.getDefault();

    builder.setMainClass(classModel);

    for (ClassElement classElement : classModel) {
      switch (classElement) {
        case MethodModel mm -> builder.addMethod(mm);
        case FieldModel fm -> builder.addField(fm);
        case AccessFlags accessFlags -> builder.addAccessFlags(accessFlags);
        case ClassFileVersion classFileVersion -> builder.addClassFileVersion(classFileVersion);
        case CustomAttribute<?> customAttribute -> builder.addCustomAttribute(customAttribute);
        case Interfaces interfaces -> builder.addInterfaces(interfaces);
        case Superclass superclass -> builder.addSuperclass(superclass);
        case CompilationIDAttribute compilationIDAttribute ->
            builder.addCompilationIDAttribute(compilationIDAttribute);
        case DeprecatedAttribute deprecatedAttribute ->
            builder.addDeprecatedAttribute(deprecatedAttribute);
        case EnclosingMethodAttribute enclosingMethodAttribute ->
            builder.addEnclosingMethodAttribute(enclosingMethodAttribute);
        case InnerClassesAttribute innerClassesAttribute ->
            builder.addInnerClassesAttribute(innerClassesAttribute);
        case ModuleAttribute moduleAttribute -> builder.addModuleAttribute(moduleAttribute);
        case ModuleHashesAttribute moduleHashesAttribute ->
            builder.addModuleHashesAttribute(moduleHashesAttribute);
        case ModuleMainClassAttribute moduleMainClassAttribute ->
            builder.addModuleMainClassAttribute(moduleMainClassAttribute);
        case ModulePackagesAttribute modulePackagesAttribute ->
            builder.addModulePackagesAttribute(modulePackagesAttribute);
        case ModuleResolutionAttribute moduleResolutionAttribute ->
            builder.addModuleResolutionAttribute(moduleResolutionAttribute);
        case ModuleTargetAttribute moduleTargetAttribute ->
            builder.addModuleTargetAttribute(moduleTargetAttribute);
        case NestHostAttribute nestHostAttribute -> builder.addNestHostAttribute(nestHostAttribute);
        case NestMembersAttribute nestMembersAttribute ->
            builder.addNestMembersAttribute(nestMembersAttribute);
        case PermittedSubclassesAttribute permittedSubclassesAttribute ->
            builder.addPermittedSubclassesAttribute(permittedSubclassesAttribute);
        case RecordAttribute recordAttribute -> builder.addRecordAttribute(recordAttribute);
        case RuntimeInvisibleAnnotationsAttribute runtimeInvisibleAnnotationsAttribute ->
            builder.addRuntimeInvisibleAnnotationsAttribute(runtimeInvisibleAnnotationsAttribute);
        case RuntimeInvisibleTypeAnnotationsAttribute runtimeInvisibleTypeAnnotationsAttribute ->
            builder.addRuntimeInvisibleTypeAnnotationsAttribute(
                runtimeInvisibleTypeAnnotationsAttribute);
        case RuntimeVisibleAnnotationsAttribute runtimeVisibleAnnotationsAttribute ->
            builder.addRuntimeVisibleAnnotationsAttribute(runtimeVisibleAnnotationsAttribute);
        case RuntimeVisibleTypeAnnotationsAttribute runtimeVisibleTypeAnnotationsAttribute ->
            builder.addRuntimeVisibleTypeAnnotationsAttribute(
                runtimeVisibleTypeAnnotationsAttribute);
        case SignatureAttribute signatureAttribute ->
            builder.addSignatureAttribute(signatureAttribute);
        case SourceDebugExtensionAttribute sourceDebugExtensionAttribute ->
            builder.addSourceDebugExtensionAttribute(sourceDebugExtensionAttribute);
        case SourceFileAttribute sourceFileAttribute ->
            builder.addSourceFileAttribute(sourceFileAttribute);
        case SourceIDAttribute sourceIDAttribute -> builder.addSourceIDAttribute(sourceIDAttribute);
        case SyntheticAttribute syntheticAttribute ->
            builder.addSyntheticAttribute(syntheticAttribute);
        case UnknownAttribute unknownAttribute -> builder.addUnknownAttribute(unknownAttribute);
      }
    }
    return builder.build();
  }
}
