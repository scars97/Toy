package poke.squad.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.domain.PokeNameListDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class PokeNameList {

    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/";
    private int limit;
    private int offset;

    public PokeNameListDto findAll(int number) throws JsonProcessingException {

        limit = 50;

        offset = limit * (number - 1);

        if (offset == 1000) {
            limit = 10;
        } else if (offset > 1000 || number == 0){
            throw new IllegalArgumentException("데이터가 없습니다.");
        }

        URI uri = UriComponentsBuilder
                .fromHttpUrl(API_BASE_URL)
                .path("/pokemon-form")
                .queryParam("offset", offset)
                .queryParam("limit", limit)
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("User-Agent", "GetPokeInfo")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> data = restTemplate.exchange(req, String.class);

        String jsonStr = data.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode root = objectMapper.readTree(jsonStr);

        JsonNode results = root.get("results");
        List<String> pokeNames = new ArrayList<>();
        for (JsonNode pokeNode : results) {
            String pokeName = pokeNode.get("name").asText();
            pokeNames.add(pokeName);
        }

        return PokeNameListDto.builder()
                .pokemons(pokeNames)
                .build();
    }
}
