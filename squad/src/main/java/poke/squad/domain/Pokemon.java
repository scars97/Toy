package poke.squad.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
public class Pokemon {

    private Long id;
    private String name;
    private List<String> types;

    @Builder
    public Pokemon(Long id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }
}
