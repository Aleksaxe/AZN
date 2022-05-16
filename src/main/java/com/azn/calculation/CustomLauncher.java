package com.azn.calculation;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;

public class CustomLauncher extends Launcher {


  public static void main(String[] args) {
    new CustomLauncher().dispatch(args);
  }

  @Override
  public void beforeStoppingVertx(Vertx vertx) {
    vertx.close();
  }

  @Override
  public void handleDeployFailed(Vertx vertx, String mainVerticle, DeploymentOptions deploymentOptions,
                                 Throwable cause) {
    vertx.close();
  }
}
