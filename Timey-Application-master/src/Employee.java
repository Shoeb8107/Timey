import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Employee extends User {
    private String start;
    private String date;
    private double payRate;
    private boolean clockedIn;
    private ArrayList<Monthly> monthlyPayslips;
    private boolean retrievedPayslips;

    public Employee(String uname, String pword, String NInum){
        super(uname, pword, 3, NInum);
        payRate = 12.50;
        clockedIn = false;
        retrievedPayslips = false;
    }

    public void clockIn(){
        Date d = new Date();
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dT = new SimpleDateFormat("HH:mm");
        date = dF.format(d);
        start = dT.format(d);
        System.out.println(start + "\n" + date);
        clockedIn = true;
    }

    public boolean clockOut(){
        if (!clockedIn){
            System.out.println("hi");
            return false;
        }
        Date d = new Date();
        DateFormat dT = new SimpleDateFormat("HH:mm");
        String end = dT.format(d);
        String user = getUsername();
        String NInum = getNI();
        System.out.println(end + user + NInum + payRate);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO VerifyPayslips (startTime, endTime, date, user, NInumber, payRate, acc) " +
                    "VALUES ('"+ start + "', '" + end + "', '" + date + "', '" + user + "', '" + NInum + "', " + payRate + ","+ 0 +");";
            stmt.executeUpdate(sql);
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Monthly> viewPayslips(){
        if(retrievedPayslips){
            return monthlyPayslips;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Monthly " +
                    "WHERE user = '"+getUsername()+"';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                monthlyPayslips.add(new Monthly(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
            }
            retrievedPayslips = true;
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return monthlyPayslips;
    }
}
