package com.zubergu.jchip8.model.instructions;

import com.zubergu.jchip8.model.Registers;

public class NnnInstructionGroup implements Instruction {
    
    private Registers registers;

    public NnnInstructionGroup( Registers r ) {
        this.registers = r;
    }
    
    @Override
    public void execute( int code ) {
        int msNibble = (code >> 12) & 0x0F;
        int nnn = code & 0x0FFF;
        
        switch( msNibble ) {
            case 0x0: sysAddr(); break;
            case 0x1: jpAddr( nnn ); break;
            case 0x2: callAddr( nnn ); break;
            case 0xA: ldiAddr( nnn ); break;
            case 0xB: jpV0Addr( nnn ); break;
            default:
                throw new RuntimeException( "Instruction 0x:" + 
                    Integer.toHexString( code ).toUpperCase() + 
                    " is not handled." );
        }
    }
    
    private void sysAddr() {
        return; // instruction 0nnn should be ignored by intepreters
    }
    
    private void jpAddr( int nnn ) {
        registers.setPC( nnn );        
    }
    
    private void callAddr( int nnn ) {
        int currentPC = registers.getPC();
        registers.pushStack( currentPC );
        registers.setPC( nnn );        
    }
    
    private void ldiAddr( int nnn ) {
        registers.setI( nnn );       
    }
    
    private void jpV0Addr( int nnn ) {
        registers.setPC( ( registers.getV( 0 ) + nnn ) & 0xFFFF );        
    }
}
