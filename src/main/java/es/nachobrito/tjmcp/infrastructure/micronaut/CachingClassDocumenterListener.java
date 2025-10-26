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

package es.nachobrito.tjmcp.infrastructure.micronaut;

import es.nachobrito.tjmcp.domain.service.CachingClassDocumenter;
import es.nachobrito.tjmcp.domain.service.ClassDocumenter;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import java.nio.file.Path;

/**
 * @author nacho
 */
@Singleton
@Requires(property = "tjmcp.cache-folder")
public class CachingClassDocumenterListener implements BeanCreatedEventListener<ClassDocumenter> {
  private final Path cacheFolder;

  public CachingClassDocumenterListener(@Value("${tjmcp.cache-folder}") String cacheFolderPath) {
    this.cacheFolder = Path.of(cacheFolderPath);
  }

  @Override
  public ClassDocumenter onCreated(@NonNull BeanCreatedEvent<ClassDocumenter> event) {
    return new CachingClassDocumenter(cacheFolder, event.getBean());
  }
}
