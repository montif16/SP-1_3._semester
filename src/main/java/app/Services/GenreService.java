package app.Services;

import app.DAO.GenreDAO;
import app.Entities.Genre;

public class GenreService {
    private final GenreDAO genreDAO;

    public GenreService() {
        this.genreDAO = new GenreDAO();
    }

    public void saveGenre(Genre genre) {
        genreDAO.save(genre);
    }

    public Genre getGenreById(Long id) {
        return genreDAO.findById(id);
    }
}
