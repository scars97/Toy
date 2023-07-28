package poke.squad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.api.connect.PokeApiJo;
import poke.squad.domain.PokeInfoDto;
import poke.squad.service.PokeService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/poke/api")
public class PokeApiController {

    private final PokeApiJo jo;
    private final PokeService pokeService;

    @GetMapping("/json/{name}")
    public ResponseEntity<PokeInfoDto> useJsonObject(@PathVariable String name) {
        PokeInfoDto poke = jo.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<PokeInfoDto> findOne(@PathVariable String name) throws JsonProcessingException {
        PokeInfoDto poke = pokeService.findOne(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/list/{number}")
    public ResponseEntity<List<PokeInfoDto>> findAll(@PathVariable int number) throws JsonProcessingException {
        List<PokeInfoDto> pokeList = pokeService.findAll(number);

        return ResponseEntity.ok().body(pokeList);
    }
}
