package poke.squad.api.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class PokeInfo {

    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/";
    private int limit;
    private int offset;

    public String getPokeInfo(String name) throws JsonProcessingException {

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

        return result.getBody();
    }

    public String getPokeList(int number) throws JsonProcessingException {

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

        return data.getBody();
    }
}
