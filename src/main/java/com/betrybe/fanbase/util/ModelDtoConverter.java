package com.betrybe.fanbase.util;

import com.betrybe.fanbase.controller.dto.SeriesCharacterCreationDto;
import com.betrybe.fanbase.controller.dto.SeriesCharacterDto;
import com.betrybe.fanbase.model.SeriesCharacter;

public class ModelDtoConverter {

  public static SeriesCharacterDto modelToDto(SeriesCharacter seriesCharacter) {
    return new SeriesCharacterDto(
        seriesCharacter.getId(), seriesCharacter.getName(), seriesCharacter.getHeight(), seriesCharacter.getDescription()
    );
  }

  public static SeriesCharacter dtoToModel(SeriesCharacterCreationDto seriesCharacterCreationDto) {
    SeriesCharacter seriesCharacter = new SeriesCharacter();

    seriesCharacter.setName(seriesCharacterCreationDto.name());
    seriesCharacter.setHeight(seriesCharacterCreationDto.height());
    seriesCharacter.setDescription(seriesCharacterCreationDto.description());
    return seriesCharacter;
  }

}
