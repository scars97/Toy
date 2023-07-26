package poke.squad.api.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import poke.squad.api.connect.PokeInfo;
import poke.squad.domain.PokeInfoDto;
import poke.squad.domain.PokeNameListDto;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PokeInfoResult {

    private final PokeInfo pokeInfo;

    public PokeInfoDto findOne(String name) throws JsonProcessingException {

        //통신으로 받은 데이터
        String jsonStr = pokeInfo.getPokeInfo(name);

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

    public PokeNameListDto findAll(int number) throws JsonProcessingException {

        String jsonStr = pokeInfo.getPokeList(number);

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
