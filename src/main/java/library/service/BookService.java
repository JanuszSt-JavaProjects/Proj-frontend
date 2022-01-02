package library.service;


import library.dto.bookDto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final RestTemplate restTemplate;

    public List<BookDto> getAll() {

        URI uri = setURI("library/books");
        return Arrays.asList(restTemplate.getForObject(uri, BookDto[].class));

    }

/*    public BookDto save(BookDto bookDto){


    }*/



    private URI setURI(String endPoint) {
        String targetUrl = "http://localhost:8080/".concat(endPoint);
        return UriComponentsBuilder
                .fromHttpUrl(targetUrl)
                .build()
                .encode()
                .toUri();
    }
}
