import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Accountant extends User {
    private ArrayList<Payslip> payslips;

    public Accountant(String uname, String pword){
        super(uname, pword, 2, null);
    }

    public ArrayList<Payslip> viewPayslip(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VerifyPayslips " +
                    "WHERE acc = "+1+";");
            while (rs.next()){
                payslips.add(new Payslip(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)));
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return payslips;
    }

    public void editStart(int id, String newStart){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE VerifyPayslips " +
                    "SET startTime = '"+newStart+"' " +
                    "WHERE id = "+id+";";
            stmt.executeUpdate(sql);
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void editEnd(int id, String newEnd){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE VerifyPayslips " +
                    "SET endTime = '"+newEnd+"' " +
                    "WHERE id = "+id+";";
            stmt.executeUpdate(sql);
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void submit(int id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE VerifyPayslips " +
                    "SET acc = "+0+" " +
                    "WHERE id = "+id+";";
            stmt.executeUpdate(sql);
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
