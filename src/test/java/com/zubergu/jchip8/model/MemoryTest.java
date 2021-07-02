package com.zubergu.jchip8.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;
import org.testng.Reporter;

public class MemoryTest {
    
    @Test
    public void verifyCorrectInitialization() {
        Memory ram = new Memory( 10 );
        int sum = 0;
        
        for( int i = 0; i<10; i++ ) {
            sum += ram.read8Bit( i );
        }
        
        assertEquals( sum, 0 );
    }
    
    
    @Test
    public void verifyWrite8BitWorks() {
        Memory ram = new Memory( 10 );
        ram.write8Bit( 9, 0xFF );
        
        assertEquals( ram.read8Bit( 9 ), 0xFF );
        assertEquals( ram.read8Bit( 0 ), 0 );
    }
    
    @Test
    public void verifyWrite16BitWorks() {
        Memory ram = new Memory( 10 );
        ram.write16Bit( 0, 0xEEFF );
        
        assertEquals( ram.read8Bit( 0 ), 0xEE );
        assertEquals( ram.read8Bit( 1 ), 0xFF );
    }
    
    @Test
    public void verifyRead16BitWorks() {
        Memory ram = new Memory( 10 );
        ram.write8Bit( 0, 0xAB );
        ram.write8Bit( 1, 0xCD );
        
        assertEquals( ram.read8Bit( 0 ), 0xAB );
        assertEquals( ram.read8Bit( 1 ), 0xCD );
        
        assertEquals( ram.read16Bit( 0 ), 0xABCD );
    }
    
    @Test( expectedExceptions = IndexOutOfBoundsException.class )
    public void verifyReadIndexTooLarge() {
        Memory ram = new Memory( 10 );
        ram.read8Bit( 10 );
    }
    
    @Test( expectedExceptions = IndexOutOfBoundsException.class )
    public void verifyWriteIndexTooLarge() {
        Memory ram = new Memory( 10 );
        ram.write16Bit( 9, 0xABCD );
    }
    
    @Test
    public void verifyInitializeFontWorks() {
        Memory ram = new Memory( 80 );
        ram.initializeFont();
        // visual inspection
        for( int i = 0; i<80; i++ ) {
            Reporter.log( 
                String.format( "%8s <br>", Integer.toBinaryString( ram.read8Bit( i ) ) ).replaceAll(" ", "0" ),
                true );   
        }
    }
    
    
}
