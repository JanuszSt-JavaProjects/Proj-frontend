package library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.dto.BookDto;
import library.dto.BorrowDto;
import library.dto.ConvertedBorrowDto;
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
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BorrowService {


    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    String route_Base = "http://localhost:8080/library/borrows";


    public List<BorrowDto> getAll() {

        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(setURI(), BorrowDto[].class)));
    }


    public BorrowDto save(BorrowDto  borrowDto) {

        try {
            String requestBody = mapper.writeValueAsString(borrowDto);
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
        return null;
    }

    public BorrowDto update(BorrowDto borrowDto) {

        try {
            String requestBody = mapper.writeValueAsString(borrowDto);
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
