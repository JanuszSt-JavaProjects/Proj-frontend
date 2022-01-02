package library.dto.bookDto;

import library.dto.statusDto.StatusDto;
import lombok.Getter;

@Getter
public class BookCreateDto {

    private String title;
    private String author;
    private int releaseDate;

    private String signature;
    private StatusDto statusDto = StatusDto.AVAILABLE;
}
