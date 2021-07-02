package com.zubergu.jchip8.model;


public class Keyboard {
    
    public static final int NUMBER_OF_KEYS = 16;
    
    private boolean[] keyState; // true if key pressed, false otherwise
    
    /**
    *
    */
    public Keyboard() {
        keyState = new boolean[NUMBER_OF_KEYS];
    }
    
    public boolean keyPressed( int key ) {
        try {
            return keyState[key];
        } catch ( IndexOutOfBoundsException ex ) {
            System.out.println( "Key with code 0x" + Integer.toHexString( key ) + " is out of range" );
            ex.printStackTrace();
            throw ex;
        }
    }
    
    
    public void setKeyState( int key, boolean state ) {
        try {
            keyState[key] = state;
        } catch ( IndexOutOfBoundsException ex ) {
            System.out.println( "Key with code 0x" + Integer.toHexString( key ) + " is out of range" );
            ex.printStackTrace();
            throw ex;
        }
    }
    
}
