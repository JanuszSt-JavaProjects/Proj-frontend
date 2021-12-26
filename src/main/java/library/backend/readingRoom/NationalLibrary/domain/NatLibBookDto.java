package library.backend.readingRoom.NationalLibrary.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NatLibBookDto {

    private String author;
    private String title;
    private long id;

}
