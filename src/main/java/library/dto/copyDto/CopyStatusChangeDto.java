package library.dto.copyDto;

import library.dto.statusDto.StatusDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CopyStatusChangeDto {
    private long copyId;
    private final String MESSAGE = "New statusDto set!";
    private final String op = "StatusDto modification";
    private StatusDto statusDto;

    public CopyStatusChangeDto(long copyId, StatusDto statusDto) {
        this.copyId = copyId;
        this.statusDto = statusDto;
    }
}



