package com.betrybe.fanbase.database;

import com.betrybe.fanbase.model.SeriesCharacter;
import com.betrybe.fanbase.util.SeriesCharacterLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SeriesCharacterFakeDatabase {

  private final Map<Long, SeriesCharacter> characters;

  SeriesCharacterFakeDatabase(SeriesCharacterLoader characterLoader) {
    List<SeriesCharacter> seriesCharacterList = characterLoader.fromClasspath("static/characters.json");

    for (int i = 0; i < seriesCharacterList.size(); i++) {
      long id = (long) (i + 1);
      seriesCharacterList.get(i).setId(id);
    }

    characters = seriesCharacterList.stream()
        .collect(Collectors.toMap(SeriesCharacter::getId, Function.identity()));

  }

  public Optional<SeriesCharacter> getCharacter(Long id) {
    return Optional.ofNullable(characters.get(id));
  }

  public SeriesCharacter save(SeriesCharacter seriesCharacter) {
    SeriesCharacter newSeriesCharacter = deepCopy(seriesCharacter);

    if (newSeriesCharacter.getId() == null) {
      Long newId = characters.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
      newSeriesCharacter.setId(newId);
    }

    characters.put(newSeriesCharacter.getId(), newSeriesCharacter);

    return newSeriesCharacter;
  }

  private SeriesCharacter deepCopy(SeriesCharacter seriesCharacter) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(objectMapper.writeValueAsString(seriesCharacter), SeriesCharacter.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public List<SeriesCharacter> getAll() {
    return characters.values().stream().toList();
  }
}
