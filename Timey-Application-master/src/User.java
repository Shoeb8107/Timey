public class User {
    private String username;
    private String password;
    private int type;
    private String NINumber;

    public User(String uname, String pword, int access, String NInum){
        username = uname;
        password = pword;
        type = access;
        NINumber = NInum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pword) {
        password = pword;
    }

    public int getType(){
        return type;
    }

    public String getNI(){
        return NINumber;
    }

    public void setNI(String NInum){
        NINumber = NInum;
    }
}
