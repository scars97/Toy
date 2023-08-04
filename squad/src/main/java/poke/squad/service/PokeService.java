package poke.squad.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import poke.squad.api.process.PokeInfoResult;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokeService {

    private final PokeInfoResult pokeInfoResult;

    public PokeInfoDto findOne(String name) throws JsonProcessingException {
        return pokeInfoResult.findOne(name);
    }

    public List<PokeInfoDto> findAll(int number) throws JsonProcessingException {
        PokeNameListDto all = pokeInfoResult.findAll(number);

        List<PokeInfoDto> pokeList = new ArrayList<>();
        for (int i = 0; i < all.getPokemons().size(); i++) {
            Long pokeId = findOne(all.getPokemons().get(i)).getId();
            String pokeName = findOne(all.getPokemons().get(i)).getName();
            String pokeImg = findOne(all.getPokemons().get(i)).getImg();
            PokeInfoDto pokeInfoDto = PokeInfoDto.builder()
                    .id(pokeId)
                    .name(pokeName)
                    .img(pokeImg)
                    .build();
            pokeList.add(pokeInfoDto);
        }

        return pokeList;
    }
}
