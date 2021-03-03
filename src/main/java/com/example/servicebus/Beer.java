package com.example.servicebus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@DataObject(generateConverter = true)
@Data
@AllArgsConstructor
@ToString
public class Beer {

  String name;
  String style;
  int price;

  public Beer(JsonObject jsonObject) {
    BeerConverter.fromJson(jsonObject, this);
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    BeerConverter.toJson(this, json);
    return json;
  }

}
