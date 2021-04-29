import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Report {
    private String month;
    private int totalHours;
    private double totalPay;

    public Report(String m, int h, double p){
        month = m;
        totalHours = h;
        totalPay = p;
    }

    public String getMonth(){
        String sMonth = "";
        try {
            Date d = new SimpleDateFormat("MM/yyyy").parse(sMonth);
            DateFormat dF = new SimpleDateFormat("MMMM yyyy");
            sMonth = dF.format(d);
        }
        catch (ParseException e){

        }
        return sMonth;
    }

    public int getHours() {
        return totalHours;
    }

    public double getPay() {
        return totalPay;
    }
}
