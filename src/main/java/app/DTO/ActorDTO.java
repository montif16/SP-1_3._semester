package app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDTO {
    private Long id;
    private String name;
    private String profile_path; // URL for actor images
}
