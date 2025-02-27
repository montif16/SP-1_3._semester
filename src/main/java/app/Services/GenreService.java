package app.Services;

import app.DAO.GenreDAO;
import app.Entities.Genre;

public class GenreService {
    private final GenreDAO genreDAO;

    public GenreService() {
        this.genreDAO = new GenreDAO();
    }

    public void addGenre(Genre genre) {
        genreDAO.save(genre);
    }

    public Genre getGenreByName(String name) {
        return genreDAO.findByName(name);
    }
}
