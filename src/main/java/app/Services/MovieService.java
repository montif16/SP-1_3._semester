package app.Services;

import app.DAO.MovieDAO;
import app.Entities.Movie;

import java.util.List;

public class MovieService {
    private final MovieDAO movieDAO;

    public MovieService() {
        this.movieDAO = new MovieDAO();
    }

    public void addMovie(Movie movie) {
        movieDAO.save(movie);
    }

    public Movie getMovieById(Long id) {
        return movieDAO.findById(id);
    }

    public Movie getMovieByTitle(String title) {
        return movieDAO.findByTitle(title);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }

    public void updateMovie(Movie movie) {
        movieDAO.update(movie);
    }

    public void deleteMovie(Long id) {
        movieDAO.delete(id);
    }
}