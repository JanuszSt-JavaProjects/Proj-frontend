package library.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ConvertedBorrowDto {

    private int id;
    private int clientId;
    private int bookId;
    private int copyId;
    private String borrowDate;
    private String returnDate;

}
