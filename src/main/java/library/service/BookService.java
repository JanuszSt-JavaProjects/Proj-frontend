package library.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import library.dto.bookDto.BookDto;
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
public class BookService {
    private final RestTemplate restTemplate;


    public List<BookDto> getAll() {

        URI uri = setURI("library/books");
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(uri, BookDto[].class)));

    }

    public void save(BookDto bookDto) {
        String uri = "library/books";


        ObjectMapper mapper = new ObjectMapper();

        try {
            String requestBody = mapper.writeValueAsString(bookDto);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/library/books"))
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


    private URI setURI(String endPoint) {
        String targetUrl = "http://localhost:8080/".concat(endPoint);
        return UriComponentsBuilder
                .fromHttpUrl(targetUrl)
                .build()
                .encode()
                .toUri();
    }
}
