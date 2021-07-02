package com.zubergu.jchip8.model.instructions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import com.zubergu.jchip8.model.*;

public class XyInstructionGroupTest {
    
    @Test
    public void subnVxVyTest() {
        Memory m = new Memory();
        Display d = new Display();
        Keyboard k = new Keyboard();
        Registers r = new Registers();
        XyInstructionGroup instruction = new XyInstructionGroup( r );
        Instruction decoded;
        Chip c = new Chip( m, d, k, r );
        
        r.setV( 0, 1 );
        r.setV( 1, 2 );
        r.setV( 0xF, 0 );
        
        decoded = c.decode( 0x8017 );
        System.out.println( "Decoded instruction proper type: " + ( decoded instanceof XyInstructionGroup ) );
        decoded.execute( 0x8017 );
        
        assertEquals( r.getV( 0 ), 1 );
        assertEquals( r.getV( 0xF ), 1 );
    }
    
    @Test
    public void shrVxVyTest() {
        Memory m = new Memory();
        Display d = new Display();
        Keyboard k = new Keyboard();
        Registers r = new Registers();
        XyInstructionGroup instruction = new XyInstructionGroup( r );
        Instruction decoded;
        Chip c = new Chip( m, d, k, r );
        
        r.setV( 0, 1 );
        r.setV( 0xF, 0 );
        
        decoded = c.decode( 0x8016 );
        System.out.println( "Decoded instruction proper type: " + ( decoded instanceof XyInstructionGroup ) );
        decoded.execute( 0x8016 );
        
        assertEquals( r.getV( 0 ), 0 );
        assertEquals( r.getV( 0xF ), 1 );
    }
    
    @Test
    public void shlVxVyTest() {
        Memory m = new Memory();
        Display d = new Display();
        Keyboard k = new Keyboard();
        Registers r = new Registers();
        XyInstructionGroup instruction = new XyInstructionGroup( r );
        Instruction decoded;
        Chip c = new Chip( m, d, k, r );
        
        r.setV( 0, 1 );
        r.setV( 0xF, 0 );
        
        decoded = c.decode( 0x801E );
        System.out.println( "Decoded instruction proper type: " + ( decoded instanceof XyInstructionGroup ) );
        decoded.execute( 0x801E );
        
        assertEquals( r.getV( 0 ), 2 );
        assertEquals( r.getV( 0xF ), 0 );
    }
}
