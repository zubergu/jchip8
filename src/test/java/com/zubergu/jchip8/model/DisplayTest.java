package com.zubergu.jchip8.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class DisplayTest {
    
    @Test
    public void verifySetPixelWorks() {
        Display display = new Display();
        
        assertFalse( display.getPixel( 1, 1 ) );
        display.setPixel( 1, 1, true );
        assertTrue( display.getPixel( 1, 1 ) );
        display.setPixel( 1, 1, false );
        assertFalse( display.getPixel( 1, 1 ) );
    }
    
}
