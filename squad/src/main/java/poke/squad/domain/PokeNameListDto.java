package poke.squad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PokeNameListDto {

    private List<String> pokemons;

    @Builder
    public PokeNameListDto(List<String> pokemons) {
        this.pokemons = pokemons;
    }
}
