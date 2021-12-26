package library.backend.readingRoom.reservation.domain.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderedBookDto {

    private String author;
    private String title;
}
