package app.Services;

import app.DAO.MovieDAO;
import app.DTO.MovieDTO;
import app.Entities.Movie;
import app.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

public class MovieService {
    private final MovieDAO movieDAO;
    private final EntityManagerFactory entityManagerFactory;

    public MovieService() {
        this.movieDAO = new MovieDAO();
        this.entityManagerFactory = HibernateConfig.getEntityManagerFactory();
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin(); //  Start transaction

        try {
            // Check if the movie already exists
            if (movieDAO.findById(movieDTO.getId()) != null) {
                System.out.println("Movie already exists in the database: " + movieDTO.getTitle());
                entityManager.getTransaction().rollback(); //  Rollback transaction
                return;
            }

            // Convert DTO to Entity
            Movie movie = new Movie();
            movie.setId(movieDTO.getId());
            movie.setTitle(movieDTO.getTitle());
            movie.setReleaseDate(LocalDate.parse(movieDTO.getRelease_date()));
            movie.setOverview(movieDTO.getOverview());
            movie.setVoteAverage(movieDTO.getVote_average());

            //  Persist movie within the transaction
            entityManager.persist(movie);
            entityManager.getTransaction().commit(); //  Commit transaction if everything succeeds
            System.out.println("Movie saved to database: " + movie.getTitle());

        } catch (Exception e) {
            entityManager.getTransaction().rollback(); //  Rollback on error
            System.out.println("Failed to save movie: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}