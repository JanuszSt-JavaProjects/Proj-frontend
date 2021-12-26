package library.backend.library.domain.dto.copyDto;

import library.backend.library.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CopyStatusChangeDto {
    private long copyId;
    private final String MESSAGE = "New status set!";
    private final String op = "Status modification";
    private Status status;

    public CopyStatusChangeDto(long copyId, Status status) {
        this.copyId = copyId;
        this.status = status;
    }
}



