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

package es.nachobrito.jmcp.infrastructure.jte;

import es.nachobrito.jmcp.domain.service.ReportBuilder;
import es.nachobrito.jmcp.domain.service.model.ReportModel;
import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.ResourceCodeResolver;
import jakarta.inject.Singleton;

/**
 * @author nacho
 */
@Singleton
public class JteReportBuilder implements ReportBuilder {
  private TemplateEngine templateEngine;
  private static final String TEMPLATE_FILE = "ClassReport.jte";

  @Override
  public String build(ReportModel model) {
    TemplateOutput output = new StringOutput();
    getTemplateEngine().render(TEMPLATE_FILE, model, output);
    return output.toString();
  }

  private TemplateEngine getTemplateEngine() {
    if (templateEngine == null) {
      CodeResolver codeResolver = new ResourceCodeResolver("jte");
      this.templateEngine = TemplateEngine.create(codeResolver, ContentType.Plain);
    }
    return templateEngine;
  }
}
