package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {

    //Create a Router
    Router router = Router.router(vertx);

    //Mount the handler for all incoming requests at every path and Http method
    router.route().handler(context -> {
      //Get the address of the request
      String address = context.request().connection().remoteAddress().toString();
      //Get the query parameter "name"
      MultiMap queryParams = context.queryParams();
      String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
      //Write a json response
      context.json(
        new JsonObject()
          .put("name", name)
          .put("address", address)
          .put("message", "Hello " + name + " connected from " + address)
      );
    });

    //create an http server
    vertx.createHttpServer()
      //handle every request for router
      .requestHandler(router)
      //listen 8889 port with callback
      .listen(8891)
      .onSuccess(server ->
        System.out.println("Http server started on port " + server.actualPort()))
      .onFailure(server -> System.err.println("Start failed: " + server.getMessage() ));
  }
}
