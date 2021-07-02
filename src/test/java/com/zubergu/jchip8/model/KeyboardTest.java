package com.zubergu.jchip8.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class KeyboardTest {
    
    @Test
    public void verifyCorrectInitialization() {
        Keyboard kbd = new Keyboard();
        boolean state = false;
        
        for( int i = 0; i<Keyboard.NUMBER_OF_KEYS; i++ ) {
            state |= kbd.keyPressed( i );
        }
        
        assertFalse( state );
    }
    
    
    @Test
    public void verifySetWorks() {
        Keyboard kbd = new Keyboard();
        
        kbd.setKeyState( 5, true ); // key gets pressed
        assertTrue( kbd.keyPressed( 5 ) );
        kbd.setKeyState( 5, false );
        assertFalse( kbd.keyPressed( 5 ) );
    }
    
    @Test( expectedExceptions = IndexOutOfBoundsException.class )
    public void verifyExceptionThrownForIndexTooLarge() {
        Keyboard kbd = new Keyboard();     
        kbd.setKeyState( Keyboard.NUMBER_OF_KEYS, true ); // key out of range gets pressed
    }
    
}
