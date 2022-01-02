package library.dto;


import lombok.Data;

@Data
public class ConvertedCopyDto {

    private int id;
    private String signature;
    private StatusDto statusDto;
    private int bookId;
}
