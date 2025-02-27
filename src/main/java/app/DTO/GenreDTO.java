package app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
    private int id;
    private String name;
    @Override
    public String toString() {
        return name;
    }
}
