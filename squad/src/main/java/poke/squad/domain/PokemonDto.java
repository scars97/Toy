package poke.squad.domain;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PokemonDto {

    private Long id;
    private String name;
    private List<String> types;

    @Builder
    public PokemonDto(Long id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }
}
