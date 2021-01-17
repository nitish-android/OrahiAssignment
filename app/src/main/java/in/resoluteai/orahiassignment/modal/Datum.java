package in.resoluteai.orahiassignment.modal;

public class Datum {

    private String month;

    private int stat;

    public Datum(String month, int stat) {
        this.month = month;
        this.stat = stat;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

}
