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

package es.nachobrito.tjmcp.infrastructure.langchain4j;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.ollama.OllamaChatModel;
import es.nachobrito.tjmcp.ResourceUtil;
import es.nachobrito.tjmcp.domain.service.CodeExplainer;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import java.lang.classfile.CodeModel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nacho
 */
@Singleton
@Requires(property = "tjmcp.explainer", value = "langchain4j-ollama")
public class LangChain4jOllamaExplainer implements CodeExplainer {
    private final Logger log = LoggerFactory.getLogger(getClass());
  private final String baseUrl;
  private final String chatModelName;
  private final String systemPrompt;
  private final String promptTemplate;
  private final Double temperature;

  private ChatModel chatModel;

  public LangChain4jOllamaExplainer(
      @Value("${tjmcp.langchain4j.ollama.url}") String baseUrl,
      @Value("${tjmcp.langchain4j.ollama.model}") String model,
      @Value("${tjmcp.langchain4j.ollama.temperature}") String temperature,
      ResourceResolver resourceResolver) {
    this.baseUrl = baseUrl;
    this.chatModelName = model;
    this.temperature = Double.parseDouble(temperature);
    this.systemPrompt =
        ResourceUtil.getResourceAsString("langchain4j/ollama/system-prompt.txt", resourceResolver);
    this.promptTemplate =
        ResourceUtil.getResourceAsString(
            "langchain4j/ollama/prompt-template.txt", resourceResolver);

    log.info("LangChain4jOllamaExplainer initialized with ollama url={} and model={}", this.baseUrl, this.chatModelName);

  }

  @Override
  public String explainCode(CodeModel codeModel) {
    var messages =
        List.of(
            SystemMessage.from(systemPrompt),
            UserMessage.from(promptTemplate.formatted(codeModel.toDebugString())));
    var request = ChatRequest.builder().messages(messages).build();
    var response = getChatModel().chat(request);
    return response.aiMessage().text();
  }

  private ChatModel getChatModel() {
    if (chatModel == null) {
      chatModel =
          OllamaChatModel.builder()
              .baseUrl(baseUrl)
              .modelName(chatModelName)
              .temperature(temperature)
              .build();
    }
    return chatModel;
  }
}
