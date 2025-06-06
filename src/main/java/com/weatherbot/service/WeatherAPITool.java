package com.weatherbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherbot.model.Weather;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherAPITool {

    @Value("${WEATHER_API_KEY}")
    private String API_KEY;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestClient restClient;

    public WeatherAPITool() {
        this.restClient = RestClient.create();
    }

    @Tool("Get the weather forecast for a location")
    public Weather getWeather(@P("Location to get the forecast for") String location) {
        try {
            // Build the URL for the WeatherAPI
            String url = "https://api.weatherapi.com/v1/forecast.json?q=" + location + "&days=1&key=" + API_KEY;

            // Use RestClient to make the HTTP GET request
            String weatherData = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);

            // Parse the JSON response
            JsonNode rootNode = objectMapper.readTree(weatherData);

            // Extract relevant fields from the JSON response
            String city = rootNode.path("location").path("name").asText();
            String forecast = rootNode.path("current").path("condition").path("text").asText();
            int temperature = rootNode.path("current").path("temp_c").asInt();

            // Create and return a WeatherForecast object
            return new Weather(city, forecast, temperature);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
        }
    }
}
