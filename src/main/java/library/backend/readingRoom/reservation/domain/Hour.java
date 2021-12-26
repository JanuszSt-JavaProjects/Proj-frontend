package library.backend.readingRoom.reservation.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public enum Hour {
    HOUR_10_11, HOUR_11_12, HOUR_12_13, HOUR_13_14;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
}
