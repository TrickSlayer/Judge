package model;

public class PowerRole {
    String Role;
    int Power;

    public PowerRole() {
    }

    public PowerRole(String Role, int Power) {
        this.Role = Role;
        this.Power = Power;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int Power) {
        this.Power = Power;
    }
    
    
}
