package com.weatherbot.model;

public record Weather(String location,
        String forecast,
        int temperature) {

}
