package app.Services;

import app.DAO.MovieDAO;
import app.DTO.MovieDTO;
import app.Entities.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public void saveMovieFromDTO(MovieDTO movieDTO) {
        // Check if the movie already exists
        if (movieDAO.findById(movieDTO.getId()) != null) {
            System.out.println("Movie already exists in the database: " + movieDTO.getTitle());
            return;
        }

        // Convert DTO to Entity
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setVoteAverage(movieDTO.getVote_average());

        if(movieDTO.getRelease_date() !=null && movieDTO.getRelease_date().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            movie.setReleaseDate(LocalDate.parse(movieDTO.getRelease_date(),formatter));
        }

        // Save to database
        movieDAO.save(movie);
        System.out.println("Saved movie: " + movie.getTitle());
    }
}