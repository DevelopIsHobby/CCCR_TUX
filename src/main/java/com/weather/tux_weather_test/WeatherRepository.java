package com.weather.tux_weather_test;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;
//import com.machbase.jdbc.*;

@Repository
public interface WeatherRepository {
    WeatherDTO getWeatherDTO() throws Exception;
}
