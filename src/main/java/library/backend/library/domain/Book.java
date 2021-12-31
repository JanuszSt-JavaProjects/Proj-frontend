package library.backend.library.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String author;
    private int releaseDate;

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Copy> copies;


    @Override
    public String toString() {
        return "id: " +id +", "+title + ", " +author + ", " + releaseDate;

    }
}
