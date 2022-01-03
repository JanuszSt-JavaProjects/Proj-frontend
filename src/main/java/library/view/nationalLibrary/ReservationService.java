package library.view.nationalLibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ObjectMapper mapper;

    public HttpResponse<String> save(ReservationDto reservationDto) {

        try {
            String requestBody = mapper.writeValueAsString(reservationDto);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/reservations"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            return client.send(request,
                    HttpResponse.BodyHandlers.ofString());

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}