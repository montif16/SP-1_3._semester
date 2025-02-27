package app.DAO;

import app.Entities.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
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

    public Genre findByName(String name) {
        try {
            return entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(Genre genre) {
        entityManager.getTransaction().begin();
        entityManager.merge(genre);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Genre genre = findById(id);
        if (genre != null) {
            entityManager.remove(genre);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
