package com.zubergu.jchip8.model.instructions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import com.zubergu.jchip8.model.Registers;

public class RetTest {
    
    @Test
    public void executionTest() {
        Registers r = new Registers();
        Ret instruction = new Ret( r );
        
        
        r.setSP( 10 );
        r.pushStack( 25 );
        
        assertEquals( r.getSP(), 11 );
        instruction.execute( 0 );
        
        assertEquals( r.getSP(), 10 );
        assertEquals( r.getPC(), 25 );
    }
}
