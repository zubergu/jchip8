package com.zubergu.jchip8.model.instructions;

import com.zubergu.jchip8.model.Registers;
import com.zubergu.jchip8.model.Keyboard;
import com.zubergu.jchip8.model.Memory;

public class ExFxInstructionGroup implements Instruction {
    
    private Registers registers;
    private Keyboard keyboard;
    private Memory memory;

    public ExFxInstructionGroup( Registers r, Keyboard k, Memory m ) {
        this.registers = r;
        this.keyboard = k;
        this.memory = m;
    }
    
    
    @Override
    public void execute( int code ) {
        int x = (code >> 8) & 0x0F;
        int msNibble = (code >> 12) & 0x0F;
        int lsByte = code & 0x00FF;
        
        if( msNibble == 0x0E ) {
            switch ( lsByte ) {
                case 0x9E : skpVx( x ); break;
                case 0xA1 : sknpVx( x ); break;
                default: 
                    throw new RuntimeException( "Instruction 0x:" + 
                        Integer.toHexString( code ).toUpperCase() + 
                        " is not handled." );
            }
                
        } else {
            switch ( lsByte ) {
                case 0x07: ldVxDt( x ); break;
                case 0x0A: ldVxK( x ); break;
                case 0x15: ldDtVx( x ); break;
                case 0x18: ldStVx( x ); break;
                case 0x1E: addIVx( x ); break;
                case 0x29: ldFVx( x ); break;
                case 0x33: ldBVx( x ); break;
                case 0x55: ldPtrIVx( x ); break;
                case 0x65: ldVxPtrI( x ); break;
                default: 
                    throw new RuntimeException( "Instruction 0x:" + 
                        Integer.toHexString( code ).toUpperCase() + 
                        " is not handled." );
                
            }
                
        }
            
    }
    
    
    private void skpVx( int x ) {
        if( keyboard.keyPressed( registers.getV( x ) ) ) {
            registers.incrementTwicePC();
        }
    }
    
    private void sknpVx( int x ) {
        if( !keyboard.keyPressed( registers.getV( x ) ) ) {
            registers.incrementTwicePC();
        }
    }
    
    private void ldVxDt( int x ) {
        registers.setV( x, registers.getDT() );
    }
    
    private void ldVxK( int x ) {
        // TODO: how to wait for user input?
    }
    
    private void ldDtVx( int x ) {
        registers.setDT( registers.getV( x ) );
    }
    
    private void ldStVx( int x ) {
        registers.setST( registers.getV( x ) );
    }
    
    private void addIVx( int x ) {
        int value = registers.getI() + registers.getV( x );
        registers.setI( value );
    }
    
    private void ldFVx( int x ) {
        int spriteAddress = registers.getV( x ) * 5;
        registers.setI( spriteAddress & 0xFFFF );
    }
    
    private void ldBVx( int x ) {
        int value = registers.getV( x );
        int currentI = registers.getI();
     
        memory.write8Bit( currentI + 2, value%10 );      // BCD ones
        value = value / 10;
        memory.write8Bit( currentI + 1, value %10 );      // BCD tens
        value = value / 10;
        memory.write8Bit( currentI, value ); // BCD hundreds
    }
    
    private void ldPtrIVx( int x ) {
        int currentI = registers.getI();
        
        for( int i = 0; i <= x; i++ ) {
            memory.write8Bit( currentI + i, registers.getV( i ) );
        }
    }
    
    private void ldVxPtrI( int x ) {
        int currentI = registers.getI();
        
        for( int i = 0; i <= x; i++ ) {
            registers.setV( i, memory.read8Bit( currentI + i ) );
        }
    }
    

}
