package library;


import library.view.nationalLibrary.NatLibBookDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@SpringBootConfiguration
public class LibraryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }


    @Bean("selectedBook")
    public NatLibBookDto createBean(){
        return new NatLibBookDto();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
