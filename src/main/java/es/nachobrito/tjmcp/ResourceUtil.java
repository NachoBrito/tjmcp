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

package es.nachobrito.tjmcp;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author nacho
 */
public class ResourceUtil {
  public static String getResourceAsString(String path, ResourceResolver resourceResolver) {
    try {
      var loader = resourceResolver.getLoader(ClassPathResourceLoader.class).orElseThrow();
      return new String(
          loader.getResourceAsStream(path).orElseThrow().readAllBytes(), Charset.defaultCharset());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
