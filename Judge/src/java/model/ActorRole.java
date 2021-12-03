/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Acer
 */
public class ActorRole {

    private Movie Movie;
    private Actor Actor;
    private String Role;

    public ActorRole() {
    }

    public ActorRole(Movie Movie, Actor Actor, String Role) {
        this.Movie = Movie;
        this.Actor = Actor;
        this.Role = Role;
    }

    public ActorRole(int MovieID, int ActorID, String Role) {
        Movie m = new Movie();
        m.setMovieID(MovieID);
        this.Movie = m;
        Actor a = new Actor();
        a.setActorID(ActorID);
        this.Actor = a;
        this.Role = Role;
    }

    public Movie getMovie() {
        return Movie;
    }

    public int getMovieID() {
        return Movie.getMovieID();
    }

    public void setMovie(Movie Movie) {
        this.Movie = Movie;
    }

    public Actor getActor() {
        return Actor;
    }

    public int getActorID() {
        return Actor.getActorID();
    }

    public void setActor(Actor Actor) {
        this.Actor = Actor;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
}
