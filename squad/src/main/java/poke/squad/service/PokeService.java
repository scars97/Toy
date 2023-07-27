package poke.squad.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poke.squad.api.process.PokeInfoResult;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;

@Service
@RequiredArgsConstructor
public class PokeService {

    private final PokeInfoResult pokeInfoResult;

    public PokeInfoDto findOne(String name) throws JsonProcessingException {
        return pokeInfoResult.findOne(name);
    }

    public PokeNameListDto findAll(int number) throws JsonProcessingException {
        return pokeInfoResult.findAll(number);
    }
}
