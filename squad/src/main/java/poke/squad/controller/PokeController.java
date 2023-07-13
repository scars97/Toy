package poke.squad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.api.PokeApiOm;
import poke.squad.api.PokeApiJo;
import poke.squad.api.PokeEncyclopedia;
import poke.squad.domain.PokeListDto;
import poke.squad.domain.PokemonDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/poke")
public class PokeController {

    private final PokeApiJo jo;
    private final PokeApiOm om;
    private final PokeEncyclopedia pokeEncyclopedia;

    @GetMapping("/json/{name}")
    public ResponseEntity<PokemonDto> useJsonObject(@PathVariable String name) {
        PokemonDto poke = jo.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/om/{name}")
    public ResponseEntity<PokemonDto> useObjectMapper(@PathVariable String name) throws JsonProcessingException {
        PokemonDto poke = om.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/list/{number}")
    public ResponseEntity<PokeListDto> allPoke(@PathVariable int number) throws JsonProcessingException {
        PokeListDto all = pokeEncyclopedia.findAll(number);

        log.info("all={}", all);

        return ResponseEntity.ok().body(all);
    }
}
