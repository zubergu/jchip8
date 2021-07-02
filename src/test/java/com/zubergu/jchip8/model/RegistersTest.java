package com.zubergu.jchip8.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class RegistersTest {
    
    @Test
    public void verifyOperationsOnV() {
        Registers r = new Registers();
        assertEquals( r.getV( 0 ), 0 );
        r.setV( 0, 0xFF );
        assertEquals( r.getV( 0 ), 0xFF );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingV() {
        Registers r = new Registers();
        r.setV( 0, 0xFFFF );
    }
    
    @Test
    public void verifyOperationsOnI() {
        Registers r = new Registers();
        assertEquals( r.getI(), 0 );
        r.setI( 0xFFFF );
        assertEquals( r.getI(), 0xFFFF );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingI() {
        Registers r = new Registers();
        r.setI( 0xFFFFFF );
    }
    
    @Test
    public void verifyOperationsOnDT() {
        Registers r = new Registers();
        assertEquals( r.getDT(), 0 );
        r.setDT( 0xFF );
        assertEquals( r.getDT(), 0xFF );
        r.setDT( 0 );
        r.decrementDT();
        assertEquals( r.getDT(), 0x00 );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingDT() {
        Registers r = new Registers();
        r.setDT( 0xFFFF );
    }
    
    @Test
    public void verifyOperationsOnST() {
        Registers r = new Registers();
        assertEquals( r.getST(), 0 );
        r.setST( 0xFF );
        assertEquals( r.getST(), 0xFF );
        r.setST( 0 );
        r.decrementST();
        assertEquals( r.getST(), 0x00 );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingST() {
        Registers r = new Registers();
        r.setST( 0xFFFF );
    }
    
    @Test
    public void verifyOperationsOnPC() {
        Registers r = new Registers();
        assertEquals( r.getPC(), 0 );
        r.setPC( 0xFFFF );
        assertEquals( r.getPC(), 0xFFFF );
        r.incrementPC();
        assertEquals( r.getPC(), 0 );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingPC() {
        Registers r = new Registers();
        r.setPC( 0x10000 );
    }
    
    @Test
    public void verifyOperationsOnSP() {
        Registers r = new Registers();
        assertEquals( r.getSP(), 0 );
        r.setSP( 0xFF );
        assertEquals( r.getSP(), 0xFF );
    }
    
    @Test( expectedExceptions = RuntimeException.class )
    public void verifyExceptionWhileSettingSP() {
        Registers r = new Registers();
        r.setSP( 0x100 );
    }
}
