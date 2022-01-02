package library.dto.borrowDto;

import library.dto.statusDto.StatusDto;
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
    private StatusDto statusDto;
    private final LocalDate returnDate = LocalDate.now();

    public BorrowReturnDto(long borrowId, StatusDto statusDto) {
        this.borrowId = borrowId;
        this.statusDto = statusDto;
    }
}
