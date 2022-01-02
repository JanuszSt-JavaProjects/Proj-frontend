package library.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class CustomerDto {

    private long id;
    private String firstname;
    private String lastname;
    private LocalDate createAccountDate;
}
