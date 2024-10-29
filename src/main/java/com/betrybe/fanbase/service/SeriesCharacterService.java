package com.betrybe.fanbase.service;

import com.betrybe.fanbase.database.SeriesCharacterFakeDatabase;
import com.betrybe.fanbase.exception.SeriesCharacterAlreadyExistsException;
import com.betrybe.fanbase.exception.SeriesCharacterNotFoundException;
import com.betrybe.fanbase.model.SeriesCharacter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesCharacterService {
  private SeriesCharacterFakeDatabase characterFakeDatabase;

  @Autowired
  public SeriesCharacterService(SeriesCharacterFakeDatabase characterFakeDatabase) {
    this.characterFakeDatabase = characterFakeDatabase;
  }

  public SeriesCharacter create(SeriesCharacter seriesCharacter) {
    for (SeriesCharacter dbCharacter : getAllCharacters()) {
      if (dbCharacter.getName().equals(seriesCharacter.getName())) {
        throw new SeriesCharacterAlreadyExistsException();
      }
    }
    return characterFakeDatabase.save(seriesCharacter);
  }

  public SeriesCharacter get(Long id) {
    return characterFakeDatabase.getCharacter(id).orElseThrow(SeriesCharacterNotFoundException::new);
  }

  public List<SeriesCharacter> getAllCharacters() {
    return characterFakeDatabase.getAll();
  }
}
