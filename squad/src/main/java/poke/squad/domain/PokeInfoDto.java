package poke.squad.domain;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PokeInfoDto {

    private Long id;
    private String name;
    private List<String> types;
    private String img;

    @Builder
    public PokeInfoDto(Long id, String name, String img, List<String> types) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.types = types;
    }

    @Builder
    public PokeInfoDto(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
}
