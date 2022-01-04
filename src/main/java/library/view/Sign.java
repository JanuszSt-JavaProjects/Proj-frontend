package library.view;

/*
 *  Project requirement : singleton pattern
 */
public final class Sign {

    private static Sign sign;

    private Sign() {
    }

    public static Sign getInstance() {
        if (sign == null) {
            sign = new Sign();
        }
        return sign;
    }

    @Override
    public String toString(){
        return "Project by: Janusz Stolarski :-)";
    }

}