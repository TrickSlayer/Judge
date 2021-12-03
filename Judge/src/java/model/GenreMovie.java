package model;

public class GenreMovie {
    private Movie Movie;
    private Genre Genre;

    public GenreMovie() {
    }

    public GenreMovie(Movie Movie, Genre Genre) {
        this.Movie = Movie;
        this.Genre = Genre;
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

    public Genre getGenre() {
        return Genre;
    }
    
    public int getGenreID() {
        return Genre.getGenreId();
    }

    public void setGenre(Genre Genre) {
        this.Genre = Genre;
    }
    
    
}
