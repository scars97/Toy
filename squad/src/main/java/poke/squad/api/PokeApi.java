package poke.squad.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.domain.Pokemon;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class PokeApi {

    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/";

    public Pokemon getPokemonByName(String name) {

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

        JSONObject json = new JSONObject(result.getBody());

        Long pokeId = Long.valueOf(json.getInt("id"));
        String pokeName = json.getString("name");
        JSONArray types = json.getJSONArray("types");

        List<String> typeNames = new ArrayList<>();
        for (int i = 0; i < types.length(); i++) {
            JSONObject bigType = types.getJSONObject(i);
            JSONObject smallType = bigType.getJSONObject("type");
            String typeName = smallType.getString("name");

            typeNames.add(typeName);
        }

        return Pokemon.builder()
                .id(pokeId)
                .name(pokeName)
                .types(typeNames)
                .build();
    }
}