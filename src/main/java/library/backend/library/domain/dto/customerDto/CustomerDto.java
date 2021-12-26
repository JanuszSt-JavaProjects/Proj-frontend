package library.backend.library.domain.dto.customerDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {

    private long clientId;
    private String firstname;
    private String lastname;
    private LocalDate createAccountDate;
}
