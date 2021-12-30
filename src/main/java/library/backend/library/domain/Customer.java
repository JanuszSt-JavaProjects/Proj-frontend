package library.backend.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    private String firstname;
    private String lastname;
    private LocalDate createAccountDate;

    @OneToMany(mappedBy = "customer")
    Set<Borrow> borrows = new HashSet<>();

    @Override
    public String toString() {
        return firstname + "  " + lastname;
    }
}
