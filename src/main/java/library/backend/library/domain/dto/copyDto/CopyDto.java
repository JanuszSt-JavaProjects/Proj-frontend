package library.backend.library.domain.dto.copyDto;

import library.backend.library.domain.Status;
import lombok.Data;

@Data
public class CopyDto {
    private long id;
    private String signature;
    private Status status;
}
