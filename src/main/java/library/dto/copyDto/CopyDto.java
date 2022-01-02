package library.dto.copyDto;

import library.dto.statusDto.StatusDto;
import lombok.Data;

@Data
public class CopyDto {
    private long id;
    private String signature;
    private StatusDto statusDto;
}
