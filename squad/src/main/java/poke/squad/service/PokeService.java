package poke.squad.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poke.squad.api.process.PokeInfoResult;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokeService {

    private final PokeInfoResult pokeInfoResult;

    public PokeInfoDto findOne(String name) throws JsonProcessingException {
        return pokeInfoResult.findOne(name);
    }

    public List<String> findAll(int number) throws JsonProcessingException {
        PokeNameListDto all = pokeInfoResult.findAll(number);

        List<String> pokeNames = new ArrayList<>();
        for (int i = 0; i < all.getPokemons().size(); i++) {
            String poke = findOne(all.getPokemons().get(i)).getName();
            pokeNames.add(poke);
        }

        return pokeNames;
    }
}
