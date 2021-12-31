package library.backend.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Copy {

    @Id
    @GeneratedValue
    private long id;

    private String signature;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    @ManyToOne(targetEntity = Book.class)
    @JsonBackReference
    private Book book;


//    @Override
//    public  String toString(){
//        return "<id: "+id+", "+signature+", "+status+">";
//    }
}


