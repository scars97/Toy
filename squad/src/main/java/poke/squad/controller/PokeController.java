package poke.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.api.PokeApi;
import poke.squad.domain.Pokemon;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/poke")
public class PokeController {

    private final PokeApi pokeApi;

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> getPokeByName(@PathVariable String name) {
        Pokemon poke = pokeApi.getPokemonByName(name);

        log.info("poke={}", poke);

        return ResponseEntity.ok().body(poke);
    }
}
