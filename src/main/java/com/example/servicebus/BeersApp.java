package com.example.servicebus;

import io.vertx.core.Vertx;

/**
 * Service bus example.
 * how-to.vertx.io/service-proxy-howto
 */
public class BeersApp {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new BarmanVerticle(), ar -> {
      System.out.println("The barman is ready to serve you");
      vertx.deployVerticle(new DrunkVerticle(), ar2 -> {
        vertx.close();
      });
    });
  }
}
