public class Monthly {
    private String user;
    private String month;
    private int hours;
    private double pay;

    public Monthly(String u, String m, int h, double p){
        user = u;
        month = m;
        hours = h;
        pay = p;
    }

    public String getMonth(){
        return month;
    }

    public int getHours(){
        return hours;
    }

    public double getPay(){
        return pay;
    }
}
