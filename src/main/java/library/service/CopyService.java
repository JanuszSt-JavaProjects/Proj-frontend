package library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.dto.BookDto;
import library.dto.CopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CopyService {


    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    String route_Base = "http://localhost:8080/library/copies";


    public List<CopyDto> getAll() {

        CopyDto[] copies = restTemplate.getForObject(setURI(), CopyDto[].class);



        return Arrays.asList(copies);
    }


    public void save(CopyDto copyDto) {

        try {
            String requestBody = mapper.writeValueAsString(copyDto);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(route_Base))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();


            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public BookDto update(CopyDto copyDto) {

        try {
            String requestBody = mapper.writeValueAsString(copyDto);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(route_Base))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();


            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id) {
        String route_del = route_Base + "/" + id;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(route_del))
                    .DELETE()
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private URI setURI() {
        String targetUrl = route_Base;
        return UriComponentsBuilder
                .fromHttpUrl(targetUrl)
                .build()
                .encode()
                .toUri();
    }
}

