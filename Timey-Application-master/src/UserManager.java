import java.util.ArrayList;
import java.sql.*;

public class UserManager {
    private ArrayList<User> users = new ArrayList<>();
    private static UserManager uM = null;

    public static UserManager getInstance(){
        if (uM == null) {
            uM = new UserManager();
        }
        return uM;
    }

    private UserManager(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07","yIFBh4Fc07","Ta7GH1aFJv");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next()){
                if (rs.getInt(3) == 0){
                    users.add(new Admin(rs.getString(1), rs.getString(2)));
                }
                else if (rs.getInt(3) == 1){
                    users.add(new Manager(rs.getString(1), rs.getString(2), rs.getString(4)));
                }
                else if (rs.getInt(3) == 2){
                    users.add(new Accountant(rs.getString(1), rs.getString(2)));
                }
                else if (rs.getInt(3) == 3){
                    users.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(4)));
                }
            }
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public User getUser(String uname){
        User cU;
        for (int i=0; i<users.size(); i++){
            cU = users.get(i);
            if (cU.getUsername().equals(uname)){
                return cU;
            }
        }
        return null;
    }

    public User login(String uname, String pword){
        User cU;
        for (int i = 0; i<users.size(); i++){
            cU = users.get(i);
            if (cU.getUsername().equals(uname) && cU.getPassword().equals(pword)) {
                return cU;
            }
            else if (cU.getUsername().equals(uname)){
                break;
            }
        }
        return null;
    }

    public boolean addUser(User u){
        User cU;
        for (int i = 0; i<users.size(); i++){
            cU = users.get(i);
            if(u.getUsername().equals(cU.getUsername())){
                return false;
            }
        }
        if(u.getType() == 0 || u.getType() == 2) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
                Statement stmt = con.createStatement();
                String sql = "INSERT INTO Users (username, password, type) " +
                        "VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', " + u.getType() + ");";
                stmt.executeUpdate(sql);
                con.close();
                users.add(u);
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        else if (u.getType() == 1 || u.getType() == 3){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
                Statement stmt = con.createStatement();
                String sql = "INSERT INTO Users (username, password, type, NInumber) " +
                        "VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', " + u.getType() + ", '"+u.getNI()+"');";
                stmt.executeUpdate(sql);
                con.close();
                users.add(u);
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return false;
    }

    public boolean removeUser(String uname){
        User cU = users.get(0);
        int index = -1;
        for (int i = 0; i<users.size(); i++){
            cU = users.get(i);
            if (uname.equals(cU.getUsername())){
                index = i;
                break;
            }
        }
        if (index != -1){
            if (cU.getType() == 0){
                return false;
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/yIFBh4Fc07", "yIFBh4Fc07", "Ta7GH1aFJv");
                Statement stmt = con.createStatement();
                String sql = "DELETE FROM Users WHERE username = '"+uname+"'";
                stmt.executeUpdate(sql);
                con.close();
                users.remove(index);
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return false;
    }
}