package app.DAO;

import app.Entities.Actor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.util.List;

public class ActorDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ActorDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Actor actor) {
        entityManager.getTransaction().begin();
        entityManager.persist(actor);
        entityManager.getTransaction().commit();
    }

    public Actor findById(Long id) {
        return entityManager.find(Actor.class, id);
    }

    public Actor findByName(String name) {
        try {
            return entityManager.createQuery("SELECT a FROM Actor a WHERE a.name = :name", Actor.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(Actor actor) {
        entityManager.getTransaction().begin();
        entityManager.merge(actor);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Actor actor = findById(id);
        if (actor != null) {
            entityManager.remove(actor);
        }
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
