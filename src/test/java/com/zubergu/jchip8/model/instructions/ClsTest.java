package com.zubergu.jchip8.model.instructions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import com.zubergu.jchip8.model.Display;

public class ClsTest {
    
    @Test
    public void executionTest() {
        Display d = new Display();
        
        d.setPixel( 1, 1, true );
        assertTrue( d.getPixel( 1, 1 ) );
        Cls cls = new Cls( d );
        
        cls.execute( 1 );
        
        for( int r = 0; r < Display.NUMBER_OF_ROWS; r++ ) {
            for( int c = 0; c < Display.NUMBER_OF_COLUMNS; c++ ) {
                assertFalse( d.getPixel( c, r ) );
            }
        }
        
    }
}
