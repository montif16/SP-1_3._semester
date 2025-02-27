package app.DAO;

import app.Entities.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GenreDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public GenreDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
    }

    public Genre findById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
