package library.weatherClient_TO_REMOVE;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.dto.WeatherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherClient {

    private final String baseUri =
            "http://api.weatherapi.com/v1/current.json?key=94403500e1004c48a49170519211912&q=Warsaw";

    public WeatherDto getWeather() throws IOException, InterruptedException {

        URI url = UriComponentsBuilder.fromHttpUrl(baseUri)
                .build()
                .encode()
                .toUri();

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .headers("Accept", "application/json", "contentType", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = new ObjectMapper().readTree(response.body());

        return new WeatherDto(
                jsonNode.get("current").get("temp_c").asDouble(),
                jsonNode.get("current").get("condition").get("icon").textValue(),
                jsonNode.get("current").get("condition").get("text").textValue()
        );
    }
}
