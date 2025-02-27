package app.Services;

import app.DAO.DirectorDAO;
import app.Entities.Director;

public class DirectorService {
    private final DirectorDAO directorDAO;

    public DirectorService() {
        this.directorDAO = new DirectorDAO();
    }

    public void addDirector(Director director) {
        directorDAO.save(director);
    }

    public Director getDirectorByName(String name) {
        return directorDAO.findByName(name);
    }
}
