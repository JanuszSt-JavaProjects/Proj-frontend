package library.backend.readingRoom.reservation.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderedBook {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String author;
    private String title;
    private long bookId;


    private OrderedBook(String author, String title, long bookId) {
        this.author = author;
        this.title = title;
    }

    /*
     *  Project requirement: builder pattern
     * */
    public static OrderedBookBuilder builder() {
        return new OrderedBookBuilder();
    }

    public static class OrderedBookBuilder {
        private String author;
        private String title;
        private long bookId;

        private OrderedBookBuilder() {
        }

        public OrderedBookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public OrderedBookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public OrderedBookBuilder bookId(long bookId) {
            this.bookId = bookId;
            return this;
        }

        public OrderedBook build() {
            return new OrderedBook(author, title, bookId);
        }
    }
}
