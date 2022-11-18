package com.edumoca.soma.entities.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message){
        super(message);
    }
    public DataNotFoundException(){
        super();
    }
}
