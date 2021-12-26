package library.backend.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherClient weatherClient;

    @GetMapping
    public Object getWeather() throws IOException, InterruptedException {
        return weatherClient.getWeather();


    }
}