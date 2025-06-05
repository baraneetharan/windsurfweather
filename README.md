# Weather AI Bot

A Spring Boot application that uses Google Gemini 2.5 Flash AI model to provide natural language weather information.

## Features

- Natural language weather queries
- Integration with WeatherAPI.com
- AI-powered responses using Google Gemini
- RESTful API endpoints
- Deployable on Render.com

## Prerequisites

- Java 21+
- Maven
- Google Gemini API Key
- WeatherAPI.com API Key

## Environment Variables

Set the following environment variables:

```bash
GOOGLE_AI_GEMINI_API_KEY=your_gemini_api_key
WEATHERAPI_API_KEY=your_weatherapi_key
SERVER_PORT=8080
```

## Building the Project

```bash
./mvnw clean package
```

## Running the Application

```bash
java -jar target/weather-ai-bot-1.0.0.jar
```

## API Endpoints

- POST `/api/weather/ask` - Get weather information
  - Request Body: `{ "location": "New York", "query": "What's the weather like?" }`

## Deployment

The application is configured for deployment on Render.com. Use the `render.yaml` configuration file.

## Example Usage

```bash
curl -X POST http://localhost:8080/api/weather/ask \
-H "Content-Type: application/json" \
-d '{"location": "New York", "query": "What's the weather like?"}'
```
