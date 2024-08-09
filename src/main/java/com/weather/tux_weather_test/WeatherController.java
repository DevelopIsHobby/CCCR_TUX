package com.weather.tux_weather_test;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class WeatherController{
    private final WeatherRepository weatherRepository;

    @GetMapping("/index")
    public void index(Model model) throws Exception {

        WeatherDTO weatherDTO = weatherRepository.getWeatherDTO();
        System.out.println("weatherDTO = " + weatherDTO);
        model.addAttribute("data", weatherDTO);

    }
}
