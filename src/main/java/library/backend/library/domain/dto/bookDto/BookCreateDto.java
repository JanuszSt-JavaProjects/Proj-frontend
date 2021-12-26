package library.backend.library.domain.dto.bookDto;

import library.backend.library.domain.Status;
import lombok.Getter;

@Getter
public class BookCreateDto {

    private String title;
    private String author;
    private int releaseDate;

    private String signature;
    private Status status = Status.AVAILABLE;
}
