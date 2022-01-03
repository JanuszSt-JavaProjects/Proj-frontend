package library.view.nationalLibrary;


import lombok.Getter;

@Getter
public enum Hour {
    HOUR_10_11("10-11"),
    HOUR_11_12("11-12"),
    HOUR_12_13("12-13"),
    HOUR_13_14("13-14");

    private final String name;

    Hour(String name) {
        this.name = name;
    }

    public static Hour byName(String name) {
        switch (name) {
            case "10-11":
                return HOUR_10_11;
            case "11-12":
                return HOUR_11_12;
            case "12-13":
                return HOUR_12_13;
            case "13-14":
                return HOUR_13_14;
            default:
                throw new IllegalArgumentException(
                        "No required ENUM!");
        }
    }



    @Override
    public String toString() {
        return name;
    }
}
