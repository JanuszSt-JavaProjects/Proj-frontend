package library.backend.readingRoom.reservation.domain;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public enum Hour {
    HOUR_10_11("10-11"),
    HOUR_11_12("11-12"),
    HOUR_12_13("12-13"),
    HOUR_13_14("13-14");

    private String name;
    private Hour(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    Hour() {
    }

    @Override
    public String toString() {
        return name;
    }
}
