package com.github.xjs.config;

import org.springframework.http.converter.json.MappingJacksonValue;

public class JsonpMappingJacksonValue extends MappingJacksonValue {
  private String jsonpFunction ;
  public JsonpMappingJacksonValue(Object value) {
    super(value);
  }
  public String getJsonpFunction() {
    return jsonpFunction;
  }

  public void setJsonpFunction(String jsonpFunction) {
    this.jsonpFunction = jsonpFunction;
  }
}