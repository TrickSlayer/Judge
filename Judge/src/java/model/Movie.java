package model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int MovieID;
    private String Name;
    private int Year;
    private String Description;
    private String Image;
    private String Trailer;
    private List<ActorRole> Actors = new ArrayList();
    private List<Genre> Genres = new ArrayList();
    private float Score;
    private String numScore;

    public Movie() {
    }

    public Movie(String Name, int Year, String Description, String Image, String Trailer) {
        this.Name = Name;
        this.Year = Year;
        this.Description = Description;
        this.Image = Image;
        this.Trailer = Trailer;
    }

    public Movie(int MovieID, String Name, int Year, String Description, String Image, String Trailer) {
        this.MovieID = MovieID;
        this.Name = Name;
        this.Year = Year;
        this.Description = Description;
        this.Image = Image;
        this.Trailer = Trailer;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float Score) {
        this.Score = Score;
    }

    public String getNumScore() {
        return numScore;
    }

    public void setNumScore(String numScore) {
        this.numScore = numScore;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String Trailer) {
        this.Trailer = Trailer;
    }

    public List<ActorRole> getActors() {
        return Actors;
    }

    public void setActors(List<ActorRole> Actors) {
        this.Actors = Actors;
    }

    public List<Genre> getGenres() {
        return Genres;
    }

    public void setGenres(List<Genre> Genres) {
        this.Genres = Genres;
    }

}
