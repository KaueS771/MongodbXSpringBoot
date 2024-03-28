package com.kaue.usuario.infrastructure.entity;

public class BusinessException extends RuntimeException {
    private static final long servialVersionUId = 1L;
    public BusinessException(String message ){super(message);}
    public BusinessException(Throwable couse) {super(couse);}
    public BusinessException(String message, Throwable couse){super(message, couse);}

}
