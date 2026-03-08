package com.example.agent.config;

import com.example.agent.tools.AgentTool;
import com.example.agent.BacklogAgent;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.util.List;

@Configuration
public class LangChainConfig {

    /** Stub for CI: app must start without a real API key so the pod becomes Ready. */
    @Bean
    @Profile("ci")
    public AnthropicChatModel anthropicChatModelCi() {
        return AnthropicChatModel.builder()
                .apiKey("ci-placeholder-no-real-calls")
                .modelName("claude-3-5-sonnet")
                .timeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    @Profile("!ci")
    public AnthropicChatModel anthropicChatModel(
            @Value("${anthropic.api-key}") String apiKey,
            @Value("${anthropic.model}") String model,
            @Value("${anthropic.timeout-seconds:60}") Integer timeoutSeconds
    ) {
        return AnthropicChatModel.builder()
                .apiKey(apiKey)
                .modelName(model) // ex: claude-3-5-sonnet
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .build();
    }

    @Bean
    public BacklogAgent backlogAgent(AnthropicChatModel model, List<AgentTool> tools) {

        System.out.println("=== Agent tools loaded: " + tools.size() + " ===");
        tools.forEach(t -> System.out.println(" - " + t.getClass().getName()));

        return AiServices.builder(BacklogAgent.class)
                .chatModel(model)
                .tools(tools.toArray())
                .build();
    }
}