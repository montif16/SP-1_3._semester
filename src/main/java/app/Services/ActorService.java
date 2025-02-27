package app.Services;

import app.DAO.ActorDAO;
import app.Entities.Actor;

public class ActorService {
    private final ActorDAO actorDAO;

    public ActorService() {
        this.actorDAO = new ActorDAO();
    }

    public void addActor(Actor actor) {
        actorDAO.save(actor);
    }

    public Actor getActorByName(String name) {
        return actorDAO.findByName(name);
    }
}
