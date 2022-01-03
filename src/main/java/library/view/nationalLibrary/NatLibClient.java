package library.view.nationalLibrary;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NatLibClient {
    private final RestTemplate restTemplate;

    private final String baseUri = "https://data.bn.org.pl/api/institutions/bibs.json?";
    private String author = "";
    private String title = "";

    private final int limit = 30;
    private URI url;




    public List<NatLibBookDto> getBookList() {
        buildUrl();
        return restTemplate.getForObject(url, NatLibResponse.class).getBibs();
    }

    private void buildUrl() {
        url = UriComponentsBuilder.fromHttpUrl(baseUri)
                .queryParam("author", author)
                .queryParam("limit", limit)
                .queryParam("title", title)
                .build()
                .encode()
                .toUri();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
