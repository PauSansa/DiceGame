package com.sansa.dicegame.exception;

public class EmptyPasswordException extends Exception{

    public EmptyPasswordException(){
        super();
    }
    public EmptyPasswordException(String msg){
        super(msg);
    }
}
