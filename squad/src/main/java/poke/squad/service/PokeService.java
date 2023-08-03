package poke.squad.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.api.process.PokeInfoResult;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<String> restPostTest() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/post/rest-test")
                /*
                .queryParam("title", "Test Title")
                .queryParam("author", "Test Author")
                .queryParam("content", "Test Content")
                */
                .encode()
                .build()
                .toUri();

        String testResult = "{\"title\":\"Test Title\",\"author\":\"Test Author\",\"content\":\"Test Content\"}";

        RequestEntity<String> request = RequestEntity
                .post(uri)
                .body(testResult);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response;
    }
}
