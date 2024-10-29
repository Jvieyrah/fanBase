package com.betrybe.fanbase.controller;

import com.betrybe.fanbase.controller.dto.SeriesCharacterCreationDto;
import com.betrybe.fanbase.controller.dto.SeriesCharacterDto;
import com.betrybe.fanbase.model.SeriesCharacter;
import com.betrybe.fanbase.service.SeriesCharacterService;
import com.betrybe.fanbase.util.ModelDtoConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/characters")
public class SeriesCharacterController {
  private SeriesCharacterService characterService;

  @Autowired
  public SeriesCharacterController(SeriesCharacterService characterService) {
    this.characterService = characterService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SeriesCharacterDto create(@RequestBody SeriesCharacterCreationDto seriesCharacterCreationDto) {
    SeriesCharacter newSeriesCharacter = characterService.create(
        ModelDtoConverter.dtoToModel(seriesCharacterCreationDto));
    return ModelDtoConverter.modelToDto(newSeriesCharacter);
  }

  @GetMapping("/{id}")
  public SeriesCharacterDto getCharacter(@PathVariable Long id) {
    return ModelDtoConverter.modelToDto(characterService.get(id));
  }

  @GetMapping
  public List<SeriesCharacter> getAllCharacters() {
    return characterService.getAllCharacters();
  }

}
