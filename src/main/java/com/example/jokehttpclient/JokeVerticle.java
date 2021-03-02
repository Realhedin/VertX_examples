package com.example.jokehttpclient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class JokeVerticle extends AbstractVerticle {

  private HttpRequest<JsonObject> request;

//  public static void main(String[] args) {
//    Vertx vertx = Vertx.vertx();
//    vertx.deployVerticle(new JokeVerticle());
//  }

  @Override
  public void start() {
      //get webclient attached to current vertx
      request = WebClient.create(vertx)
        //http get request for path
    .get(443, "icanhazdadjoke.com", "/")
        //enable encryption
    .ssl(true)
        //which data we want
    .putHeader("Accept", "application/json")
        //convert to json
    .as(BodyCodec.jsonObject())
        //expect positive response 200
    .expect(ResponsePredicate.SC_OK);

      //set periodic
    vertx.setPeriodic(3000, id -> fetchJoke());

  }

  private void fetchJoke() {
    request.send(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result().body().getString("joke"));
        System.out.println("Smile");
        System.out.println();
      }
    });
  }
}
