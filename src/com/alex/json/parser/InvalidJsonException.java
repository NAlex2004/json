package com.alex.json.parser;

public class InvalidJsonException extends Exception {    
	private static final long serialVersionUID = 1L;
	protected int index = 0;
    
    public int getIndex() {
    	return index;
    }
    
	public InvalidJsonException() {
        super();
    }
    
    public InvalidJsonException(String message) {
        super(message);
    }
    
    public InvalidJsonException(String message, int index) {
        super(message);
        this.index =index;
    }
}