package com.weatherbot.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weatherbot.service.WeatherAPITool;

@Configuration
public class ChatModelConfig {

    @Value("${AI_API_KEY}")
    private String aiApiKey;

    @Bean
    GoogleAiGeminiChatModel gitHubModelsChatLanguageModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(aiApiKey)
                .modelName("gemini-2.0-flash")
                .temperature(0.7)
                .maxOutputTokens(1000)
                .build();
    }

    @Bean
    public Assistant assistant(GoogleAiGeminiChatModel chatLanguageModel, WeatherAPITool weatherAPI) {
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        
        return AiServices.builder(Assistant.class)
                .chatModel(chatLanguageModel)
                .chatMemory(chatMemory)
                .tools(weatherAPI)
                .build();
    }
}