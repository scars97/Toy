package poke.squad.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.domain.PokeInfoDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class PokeInfo {

    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/";

    public PokeInfoDto getPokeInfo(String name) throws JsonProcessingException {

        //Http 통신
        URI uri = UriComponentsBuilder
                .fromHttpUrl(API_BASE_URL)
                .path("pokemon-form/" + name + "/")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("User-Agent", "GetPokeInfo")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        //통신으로 받은 데이터
        String jsonStr = result.getBody();

        //Json -> Java 역직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        //joinStr 필드 값 읽어오기
        JsonNode root = objectMapper.readTree(jsonStr);

        //원하는 필드 값 가져오기
        Long pokeId = root.get("id").asLong();
        String pokeName = root.get("name").asText();
        JsonNode typesNode = root.get("types");
        List<String> types = new ArrayList<>();
        for (JsonNode typeNode : typesNode) {
            String typeName = typeNode.get("type").get("name").asText();
            types.add(typeName);
        }
        JsonNode spritesNode = root.get("sprites");
        String frontImg = spritesNode.get("front_default").asText();

        return PokeInfoDto.builder()
                .id(pokeId)
                .name(pokeName)
                .img(frontImg)
                .types(types)
                .build();
    }
}
