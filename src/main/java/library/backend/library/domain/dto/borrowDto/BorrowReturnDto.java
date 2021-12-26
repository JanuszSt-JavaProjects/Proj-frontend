package library.backend.library.domain.dto.borrowDto;

import library.backend.library.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BorrowReturnDto {
    private long borrowId;
    private final String MESSAGE = "Book return confirmed!";
    private final String op = "modification";
    private Status status;
    private final LocalDate returnDate = LocalDate.now();

    public BorrowReturnDto(long borrowId, Status status) {
        this.borrowId = borrowId;
        this.status = status;
    }
}
