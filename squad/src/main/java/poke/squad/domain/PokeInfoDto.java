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

    @Builder
    public PokeInfoDto(Long id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }
}
