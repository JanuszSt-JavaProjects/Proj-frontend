package library.dto;

import lombok.Data;

@Data
public class BookDto {

    private long id;
    private String title;
    private String author;
    private int releaseDate;

    @Override
    public String toString() {
        return
                "id: " + id +
                        ", " + title +
                        ", " + author +
                        ", " + releaseDate;

    }
}
