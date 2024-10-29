package com.betrybe.fanbase.util;

import com.betrybe.fanbase.model.SeriesCharacter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SeriesCharacterLoader {
  @Autowired
  ApplicationContext ctx;

  /**
   * Loads objects from classpath resource.
   */
  public List<SeriesCharacter> fromClasspath(String path) {
    try {
      InputStream file = ctx.getResource("classpath:" + path).getInputStream();
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(file, new TypeReference<>() {
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
