package app.DAO;

import app.Entities.Movie;
import app.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.util.List;

public class MovieDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MovieDAO() {
        this.entityManagerFactory = HibernateConfig.getEntityManagerFactory();
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

    public Movie findByTitle(String title) {
        try {
            return entityManager.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(Movie movie) {
        entityManager.getTransaction().begin();
        entityManager.merge(movie);
        entityManager.getTransaction().commit();
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
