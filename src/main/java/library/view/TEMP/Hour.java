package library.view.TEMP;


import lombok.Getter;

@Getter
public enum Hour {
    HOUR_10_11("10-11"),
    HOUR_11_12("11-12"),
    HOUR_12_13("12-13"),
    HOUR_13_14("13-14");

    private String name;

    Hour(String name) {
        this.name = name;
    }

    Hour() {
    }
    private Long id;



    @Override
    public String toString() {
        return name;
    }
}
