package com.example.servicebus;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * all magic with Event bus happens inside of generated Proxy.
 */
@VertxGen
@ProxyGen
public interface BarmanService {

  void giveMeARandomBeer(String customerName, Handler<AsyncResult<Beer>> handler);

  void getMyBill(String customerName, Handler<AsyncResult<Integer>> handler);

  void payMyBill(String customerName);

  //method will be generated
  static BarmanService createProxy(Vertx vertx, String address) {
    return new BarmanServiceVertxEBProxy(vertx, address);
  }
}
