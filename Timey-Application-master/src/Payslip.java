import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payslip{
    private int id;
    private String startTime;
    private String endTime;
    private String date;
    private String user;
    private String NInumber;
    private double payRate;
    private boolean buttonPressed;

    public Payslip(int ID, String s, String e, String d, String u, String NI, double p){
        id = ID;
        startTime = s;
        endTime = e;
        date = d;
        user = u;
        NInumber = NI;
        payRate = p;
        buttonPressed = false;
    }

    private int getHours(){
        int total;
        int sHours = Integer.parseInt(startTime.substring(0,2));
        int sMins = Integer.parseInt(startTime.substring(3));
        int eHours = Integer.parseInt(endTime.substring(0,2));
        int eMins = Integer.parseInt(endTime.substring(3));
        if (sMins > 15){
            sHours++;
        }
        if (eMins > 45){
            eHours++;
        }
        total = eHours - sHours;
        return total;
    }

    private String getMonth(){
        return date.substring(3);
    }

    private double getPay(){
        return payRate*getHours();
    }

    public void tickPayslip(){
        if (buttonPressed){
            return;
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sqlPayslip = "INSERT INTO Payslips (startTime,endTime,date,user,NInumber,payRate) " +
                    "VALUES ('"+startTime+"','"+endTime+"','"+date+"','"+user+"','"+NInumber+"',"+payRate+");";
            stmt.executeUpdate(sqlPayslip);
            String sqlMonth = "SELECT * FROM Monthly " +
                    "WHERE user = '"+user+"' " +
                    "AND month = '"+getMonth()+"';";
            ResultSet rs = stmt.executeQuery(sqlMonth);
            if (rs.next()){
                String sqlUpdate = "UPDATE Monthly " +
                        "SET hours = "+(rs.getInt(3)+getHours())+" , pay = "+(rs.getDouble(4)+getPay())+" " +
                        "WHERE user = '"+user+"' AND month = '"+getMonth()+"';";
                stmt.executeUpdate(sqlUpdate);
            }
            else{
                String sqlInsert = "INSERT INTO Monthly (user,month,hours,pay) " +
                        "VALUES ('"+user+"','"+getMonth()+"',"+getHours()+","+getPay()+");";
                stmt.executeUpdate(sqlInsert);
            }
            buttonPressed = true;
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void crossPayslip(){
        if (buttonPressed){
            return;
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE VerifyPayslips " +
                    "SET acc = "+1+" " +
                    "WHERE id = "+id+";";
            stmt.executeUpdate(sql);
            buttonPressed = true;
            con.close();
        }
        catch (Exception e){
            System.out.print(e);
        }
    }
}
