package poke.squad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;
import poke.squad.service.PokeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/poke")
public class PokeController {

    private final PokeService pokeService;

    @GetMapping("/search")
    public String search() {
        return "search-poke";
    }

    @PostMapping("/search")
    public String searchPoke(@RequestParam("pokeName") String name, RedirectAttributes redirectAttributes) {
        log.info("name={}", name);

        redirectAttributes.addAttribute("pokeName", name);
        return "redirect:/poke/info/{pokeName}";
    }

    @GetMapping("/info/{pokeName}")
    public String findOne(@PathVariable String pokeName, Model model) throws JsonProcessingException {
        PokeInfoDto poke = pokeService.findOne(pokeName);

        log.info("poke={}", poke);

        model.addAttribute("pokeInfo", poke);
        return "poke-info";
    }

    @GetMapping("/list/{num}")
    public String findAll(@PathVariable int num, Model model) throws JsonProcessingException {
        PokeNameListDto all = pokeService.findAll(num);

        List<String> pokes = new ArrayList<>();
        for (int i = 0; i < all.getPokemons().size(); i++) {
            String poke = pokeService.findOne(all.getPokemons().get(i)).getName();
            pokes.add(poke);
        }

        model.addAttribute("pokeList", pokes);
        return "poke-list";
    }
}
