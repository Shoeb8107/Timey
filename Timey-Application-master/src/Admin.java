import java.sql.*;

public class Admin extends User{
    private UserManager uM;

    public Admin(String uname, String pword){
        super(uname, pword, 0, null);
        uM = UserManager.getInstance();
    }

    public boolean createManager(String uname, String pword, String NInum){
        return uM.addUser(new Manager(uname, pword, NInum));
    }

    private User checkExists(String uname){
        User man = uM.getUser(uname);
        if (man == null){
            System.out.println("User doesn't exist");
            return null;
        }
        if (man.getType() == 1){
            return man;
        }
        return null;
    }

    public void removeManager(String uname){
        if (checkExists(uname) == null){
            System.out.println("User doesn't exist");
            return;
        }
        uM.removeUser(uname);
    }

    public boolean changePassword(String uname, String newpword){
        if (checkExists(uname) == null){
            return false;
        }
        User man = checkExists(uname);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE Users " +
                    "SET password = '"+newpword+"' " +
                    "WHERE username = '"+uname+"';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();
            man.setPassword(newpword);
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
        User man = checkExists(uname);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            String sql = "UPDATE Users " +
                    "SET NInumber = '"+newNI+"' " +
                    "WHERE username = '"+uname+"';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            con.close();
            man.setNI(newNI);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
