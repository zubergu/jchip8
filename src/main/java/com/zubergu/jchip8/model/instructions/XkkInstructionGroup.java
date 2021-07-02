package com.zubergu.jchip8.model.instructions;

import java.util.Random;
import com.zubergu.jchip8.model.Registers;

public class XkkInstructionGroup implements Instruction {
    
    private Registers registers;

    public XkkInstructionGroup( Registers r ) {
        this.registers = r;
    }
    
    @Override
    public void execute( int code ) {
        int msNibble = (code >> 12) & 0x0F;
        int x = (code >> 8) & 0x0F;
        int kk = code & 0x00FF;
        
        switch( msNibble ) {
            case 0x3: seVxByte( x, kk ); break;
            case 0x4: sneVxByte( x, kk ); break;
            case 0x6: ldVxByte( x, kk ); break;
            case 0x7: addVxByte( x, kk ); break;
            case 0xC: rndVxByte( x, kk ); break;
            default:
                throw new RuntimeException( "Instruction 0x:" + 
                    Integer.toHexString( code ).toUpperCase() + 
                    " is not handled." );
        }
    }
    
    
    private void seVxByte( int x, int kk ) {
        if( registers.getV( x ) == kk ) {
            registers.incrementTwicePC();
        }        
    }
    
    private void sneVxByte( int x, int kk ) {
        if( registers.getV( x ) != kk ) {
            registers.incrementTwicePC();
        }        
    }

    private void ldVxByte( int x, int kk ) {
        registers.setV( x, kk );        
    } 
    
    private void addVxByte( int x, int kk ) {
        registers.setV( x, ( registers.getV( x ) + kk ) & 0xFF );
    }
    
    private void rndVxByte( int x, int kk ) {
        registers.setV( x, ( new Random().nextInt( 256 ) & kk ) & 0xFF );        
    }
}
