package com.azn.calculation.verticle;

import com.azn.calculation.model.CalculationRequest;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorVerticle extends AbstractVerticle {
  @Override
  public void start() {
    Router router = Router.router(vertx);
    Gson gson = new Gson();
    router.route().handler(BodyHandler.create());
    router.post("/calculate").handler(req -> {
      String requestBodyAsString = req.body().asString();
      CalculationRequest calculationRequest = gson.fromJson(requestBodyAsString, CalculationRequest.class);
      String answer = calculate(calculationRequest);
      String response = String.format("We get a request (%s), with operation %s and answer is %s",
        requestBodyAsString,
        calculationRequest.getOperation(),
        answer
      );
      req.response().end(response);
      vertx.eventBus().request("calculator.log",response);
    });
    vertx.createHttpServer().requestHandler(router).listen(8081);
  }

  private String calculate(CalculationRequest calculationRequest) {
    String answer;
    switch (calculationRequest.operation) {
      case PLUS: return new BigDecimal(calculationRequest.getFirstNum()).add(
        new BigDecimal(calculationRequest.getSecondNum())).toString();
      case MINUS:return new BigDecimal(calculationRequest.getFirstNum()).subtract(
        new BigDecimal(calculationRequest.getSecondNum())).toString();
      case DIVIDE: return new BigDecimal(calculationRequest.getFirstNum()).divide(
        new BigDecimal(calculationRequest.getSecondNum()), RoundingMode.DOWN).toString();
      case MULTIPLY: return new BigDecimal(calculationRequest.getFirstNum()).multiply(
        new BigDecimal(calculationRequest.getSecondNum())).toString();
      default: answer = "Operation not supported";
    }
    return answer;
  }
}
