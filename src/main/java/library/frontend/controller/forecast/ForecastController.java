package library.frontend.controller.forecast;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class ForecastController {

    @GetMapping("/forecast")
    public String showForecast() {
        return "forecast.html" ;
    }
}
