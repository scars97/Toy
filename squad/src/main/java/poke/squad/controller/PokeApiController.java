package poke.squad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.api.connect.PokeInfo;
import poke.squad.api.connect.PokeApiJo;
import poke.squad.domain.PokeNameListDto;
import poke.squad.domain.PokeInfoDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/poke/api")
public class PokeApiController {

    private final PokeApiJo jo;
    private final PokeInfo pokeInfo;

    @GetMapping("/json/{name}")
    public ResponseEntity<PokeInfoDto> useJsonObject(@PathVariable String name) {
        PokeInfoDto poke = jo.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<PokeInfoDto> findOne(@PathVariable String name) throws JsonProcessingException {
        PokeInfoDto poke = pokeInfo.getPokeInfo(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }

    @GetMapping("/list/{number}")
    public ResponseEntity<List<PokeInfoDto>> findAll(@PathVariable int number) throws JsonProcessingException {
        PokeNameListDto all = pokeInfo.findAll(number);

        List<PokeInfoDto> pokes = new ArrayList<>();
        for (int i = 0; i < all.getPokemons().size(); i++) {
            PokeInfoDto poke = pokeInfo.getPokeInfo(all.getPokemons().get(i));
            pokes.add(poke);
        }

        return ResponseEntity.ok().body(pokes);
    }
}