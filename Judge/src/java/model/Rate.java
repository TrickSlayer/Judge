package model;

import java.sql.Date;

public class Rate {

    private int UserID;
    private int MovieID;
    private Date Time;
    private String Comment;
    private int Rate;

    public Rate() {
    }

    public Rate(int UserID, int MovieID, String Comment, int Rate) {
        this.UserID = UserID;
        this.MovieID = MovieID;
        this.Comment = Comment;
        this.Rate = Rate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date Time) {
        this.Time = Time;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }
}
