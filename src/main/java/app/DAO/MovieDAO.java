package app.DAO;

import app.Entities.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MovieDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MovieDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Movie movie) {
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
    }

    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Movie movie = findById(id);
        if (movie != null) {
            entityManager.remove(movie);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
