package com.azn.calculation.model;

import java.io.Serializable;

public class CalculationRequest implements Serializable {
  public String firstNum;
  public String secondNum;
  public OperationEnum operation;

  public CalculationRequest() {
  }

  public String getFirstNum() {
    return firstNum;
  }

  public void setFirstNum(String firstNum) {
    this.firstNum = firstNum;
  }

  public String getSecondNum() {
    return secondNum;
  }

  public void setSecondNum(String secondNum) {
    this.secondNum = secondNum;
  }

  public OperationEnum getOperation() {
    return operation;
  }

  public void setOperation(OperationEnum operation) {
    this.operation = operation;
  }
}
