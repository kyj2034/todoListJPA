package com.study.jpa.result;

import lombok.Data;

@Data
public class ErrorResponse {
  private String message;
  private String code;
  private Object data;
  private String sentence;
  private boolean success;

  public ErrorResponse(ResultCodeImpl code) {
    this.setMessage(code.getValue());
    this.setCode(code);
    this.setSuccess(code.isSuccess());
  }

  public ErrorResponse(ResultCodeImpl code, Object data) {
    this.setCode(code);
    this.setMessage(code.getValue());
    this.setData(data);
    this.setSuccess(code.isSuccess());
  }

  public ErrorResponse(ResultCodeImpl code,
      String sentence) {
    this.setCode(code);
    this.setMessage(code.getValue());
    this.setSentence(sentence);
    this.setSuccess(code.isSuccess());
  }

  public ErrorResponse(ResultCodeImpl code,
      String message,
      Object data) {
    this.setCode(code);
    this.setMessage(message);
    this.setData(data);
    this.setSuccess(code.isSuccess());
  }

  public ErrorResponse(ResultCodeImpl code,
      Object data,
      String sentence) {
    this.setCode(code);
    this.setMessage(code.getValue());
    this.setData(data);
    this.setSentence(sentence);
    this.setSuccess(code.isSuccess());
  }

  public static ErrorResponse of(ResultCodeImpl code) {
    return new ErrorResponse(code);
  }

  public void setCode(ResultCodeImpl code) {
    this.code = code.getKey();
  }

}
