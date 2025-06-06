package com.weatherbot.config;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("You are a polite assistant capable of providing SQL Queries, weather forecasts and answering from text files.")
    String chat(String userMessage);
}
