package edu.ithaca.dragon.bank;

public class InsufficientQuantityException extends Exception{
    private static final long serialVersionUID = 1L;

    public InsufficientQuantityException(String s) {
        super(s);
    }
}
