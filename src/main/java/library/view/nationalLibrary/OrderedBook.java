package library.view.nationalLibrary;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderedBook {

    private String author;
    private String title;
    private long bookId;
}


