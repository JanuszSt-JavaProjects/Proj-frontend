package library.dto;

import lombok.Data;

@Data
public class CopyDto {
    private long id;
    private String signature;
    private StatusDto status;
    private long bookId;
}
