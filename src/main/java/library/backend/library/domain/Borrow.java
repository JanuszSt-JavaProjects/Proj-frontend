package library.backend.library.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Borrow {
    @Id
    @GeneratedValue
    private long id;

    private long bookId;
    private long copyId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @ManyToOne(targetEntity = Customer.class)
    Customer customer;
}
