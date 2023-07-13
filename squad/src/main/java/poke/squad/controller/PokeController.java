package poke.squad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.api.ApiObjectMapper;
import poke.squad.api.PokeApi;
import poke.squad.domain.PokemonDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/poke")
public class PokeController {

    private final PokeApi pokeApi;
    private final ApiObjectMapper apiObjectMapper;

    @GetMapping("/json/{name}")
    public ResponseEntity<PokemonDto> useJsonObject(@PathVariable String name) {
        PokemonDto poke = pokeApi.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/om/{name}")
    public ResponseEntity<PokemonDto> useObjectMapper(@PathVariable String name) throws JsonProcessingException {
        PokemonDto poke = apiObjectMapper.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }
}
