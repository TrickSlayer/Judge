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
public class Genre {
    private int GenreId;
    private String Genre;
    private String Description;

    public Genre() {
    }

    public Genre(String GenreString, String Description) {
        this.Genre = GenreString;
        this.Description = Description;
    }    

    public Genre(int GenreId, String GenreString, String Description) {
        this.GenreId = GenreId;
        this.Genre = GenreString;
        this.Description = Description;
    }

    public int getGenreId() {
        return GenreId;
    }

    public void setGenreId(int GenreId) {
        this.GenreId = GenreId;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String GenreString) {
        this.Genre = GenreString;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
