package com.azn.calculation.verticle;

import io.vertx.core.AbstractVerticle;

public class LogVerticle extends AbstractVerticle {
  @Override
  public void start() {
    vertx.eventBus().consumer("calculator.log", message -> {
      System.out.println(">> " + message.body());
    });
  }
}
