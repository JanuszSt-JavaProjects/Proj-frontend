package library;


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

    @Bean("RestTem")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

/*    @Bean("selectedBook")
    public NatLibBookDto createBean(){
        return new NatLibBookDto();
    }*/
}
