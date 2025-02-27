package app.DAO;

import app.Entities.Director;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;

public class DirectorDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DirectorDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Director director) {
        entityManager.getTransaction().begin();
        entityManager.persist(director);
        entityManager.getTransaction().commit();
    }

    public Director findById(Long id) {
        return entityManager.find(Director.class, id);
    }

    public Director findByName(String name) {
        try {
            return entityManager.createQuery("SELECT d FROM Director d WHERE d.name = :name", Director.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(Director director) {
        entityManager.getTransaction().begin();
        entityManager.merge(director);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Director director = findById(id);
        if (director != null) {
            entityManager.remove(director);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
