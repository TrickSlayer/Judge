package model;

import java.sql.Date;

public class Actor {

    private int ActorID;
    private String Name;
    private Date DOB;
    private String Status;

    public Actor() {
    }

    public Actor(int ActorID, String Name, Date DOB, String Status) {
        this.ActorID = ActorID;
        this.Name = Name;
        this.DOB = DOB;
        this.Status = Status;
    }

    public Actor(String Name, Date DOB, String Status) {
        this.Name = Name;
        this.DOB = DOB;
        this.Status = Status;
    }

    public int getActorID() {
        return ActorID;
    }

    public void setActorID(int ActorID) {
        this.ActorID = ActorID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
