package poke.squad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PokeListDto {

    private String next;
    private String previous;
    private List<String> pokemons;

    @Builder
    public PokeListDto(String next, String previous, List<String> pokemons) {
        this.next = next;
        this.previous = previous;
        this.pokemons = pokemons;
    }
}
