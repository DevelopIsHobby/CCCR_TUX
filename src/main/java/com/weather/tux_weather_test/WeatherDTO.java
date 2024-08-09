package com.weather.tux_weather_test;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
    private String time;

    private String address;

    private Float temp;

    private Float feeling;

    private Float humidity;

    private String weather;

    private String dust;

    private String mini_dust;

    private String uv;

    private Float rain;
}
