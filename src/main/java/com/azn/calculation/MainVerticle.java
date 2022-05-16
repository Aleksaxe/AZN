package com.azn.calculation;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    vertx.deployVerticle("com.azn.calculation.verticle.CalculatorVerticle");
    vertx.deployVerticle("com.azn.calculation.verticle.LogVerticle");
  }
}
