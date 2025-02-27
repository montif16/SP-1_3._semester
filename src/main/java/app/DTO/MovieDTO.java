package app.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {
    private Long id;
    private String title;
    private String release_date;
    private String overview;
    private Double vote_average;
    private List<GenreDTO> genres;
}
