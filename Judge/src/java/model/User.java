package model;

public class User {
    private int UserID;
    private String UserName;
    private String Password;
    private String Avata;
    private String Role;
    private int Power;
    private String Gmail;
    
    public User() {
    }

    public User(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public User(int UserID, String UserName, String Password, String Avata, String Role, int Power) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.Avata = Avata;
        this.Role = Role;
        this.Power = Power;
    }    

    public User(int UserID, String UserName, String Password, String Avata, String Role, int Power, String Gmail) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.Avata = Avata;
        this.Role = Role;
        this.Power = Power;
        this.Gmail = Gmail;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int Power) {
        this.Power = Power;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getAvata() {
        return Avata;
    }

    public void setAvata(String Avata) {
        this.Avata = Avata;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
}
