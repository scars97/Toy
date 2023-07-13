package poke.squad.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
