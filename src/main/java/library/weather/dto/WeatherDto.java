package library.weather.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDto {

    private double temp;
    private String icon;
    private String weather;

}
