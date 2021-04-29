import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Manager extends User{
    private UserManager uM;
    private String start;
    private String date;
    private double payRate;
    private boolean clockedIn;
    private ArrayList<Monthly> monthlyPayslips;
    private boolean retrievedPayslips;
    private ArrayList<Payslip> toBeVerified;

    public Manager(String uname, String pword, String NInum){
        super(uname, pword, 1, NInum);
        payRate = 15.20;
        clockedIn = false;
        retrievedPayslips = false;
        uM = UserManager.getInstance();
    }

    public Report generateReport(String month){
        int hours = 0;
        double pay = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Monthly " +
                    "WHERE month = '"+month+"';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                hours = hours + rs.getInt(3);
                pay = pay + rs.getDouble(4);
            }
            con.close();
        }
        catch (Exception e){

        }
        return new Report(month, hours, pay);
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
            String sql = "INSERT INTO Payslips (startTime, endTime, date, user, NInumber, payRate) " +
                    "VALUES ('"+ start + "', '" + end + "', '" + date + "', '" + user + "', '" + NInum + "', " + payRate + ");";
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

    public boolean createEmployee(String uname, String pword, String NInum){
        return uM.addUser(new Employee(uname, pword, NInum));
    }

    public boolean createAccountant(String uname, String pword){
        return uM.addUser(new Accountant(uname, pword));
    }

    public void removeUser(String uname){
        User removed = uM.getUser(uname);
        if (removed == null){
            return;
        }
        if (removed.getType() == 2 || removed.getType() == 3){
            uM.removeUser(uname);
        }
    }

    public boolean changePassword(String uname, String newpword){
        if (checkExists(uname) == null){
            return false;
        }
        User emp = checkExists(uname);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE Users " +
                    "SET password = '"+newpword+"' " +
                    "WHERE username = '"+uname+"';";
            stmt.executeUpdate(sql);
            emp.setPassword(newpword);
            con.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean changeNI(String uname, String newNI){
        if (checkExists(uname) == null){
            return false;
        }
        User emp = checkExists(uname);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE Users " +
                    "SET NInumber = '"+newNI+"' " +
                    "WHERE username = '"+uname+"';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            emp.setNI(newNI);
            con.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    private User checkExists(String uname){
        User emp = uM.getUser(uname);
        if (emp == null){
            System.out.println("User doesn't exist");
            return null;
        }
        if (emp.getType() == 3){
            return emp;
        }
        return null;
    }
}
