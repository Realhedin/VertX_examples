package com.example.servicebus;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * Verticle to start barman service and initialize event bus.
 */
public class BarmanVerticle extends AbstractVerticle {

  @Override
  public void start() {
    BarmanService service = new BarmanServiceImpl(WebClient.create(vertx)); // <1>

    new ServiceBinder(vertx) // <2>
      .setAddress("beers.services.myapplication") // <3> - event bus name
      .register(BarmanService.class, service); // <4>
  }
}
