package poke.squad.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.domain.PokemonDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiObjectMapper {

    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/";

    public PokemonDto getPokemonByName(String name) throws JsonProcessingException {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(API_BASE_URL)
                .path("pokemon-form/" + name + "/")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("User-Agent", "Your-User-Agent-String")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        String jsonStr = result.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode root = objectMapper.readTree(jsonStr);

        long pokeId = root.get("id").asLong();
        String pokeName = root.get("name").asText();
        
        JsonNode typesNode = root.get("types");
        List<String> types = new ArrayList<>();
        for (JsonNode typeNode : typesNode) {
            String typeName = typeNode.get("type").get("name").asText();
            types.add(typeName);
        }

        return PokemonDto.builder()
                .id(pokeId)
                .name(pokeName)
                .types(types)
                .build();
    }
}
