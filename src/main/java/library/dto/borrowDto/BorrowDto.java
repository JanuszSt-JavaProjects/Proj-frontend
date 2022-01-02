package library.dto.borrowDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowDto {

    private long id;
    private long clientId;
    private long bookId;
    private long copyId;
    private LocalDate borrowDate;
}
