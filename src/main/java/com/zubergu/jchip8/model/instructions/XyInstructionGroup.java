package com.zubergu.jchip8.model.instructions;

import com.zubergu.jchip8.model.Registers;

public class XyInstructionGroup implements Instruction {
    
    private Registers registers;

    public XyInstructionGroup( Registers r ) {
        this.registers = r;
    }
    
    
    @Override
    public void execute( int code ) {
        int x = (code >> 8) & 0x0F;
        int y = (code >> 4) & 0x0F;
        int msNibble = (code >> 12) & 0x0F;
        int lsNibble = code & 0x000F;
        
        if( msNibble == 0x5 && lsNibble == 0x0) {
            seVxVy( x, y );
        } else if( msNibble == 0x09 && lsNibble == 0 ) {
            sneVxVy( x, y );   
        } else if ( msNibble == 0x08 ) {
            switch ( lsNibble ) {
                case 0x0: ldVxVy( x, y ); break;
                case 0x1: orVxVy( x, y ); break;
                case 0x2: andVxVy( x, y ); break;
                case 0x3: xorVxVy( x, y ); break;
                case 0x4: addVxVy( x, y ); break;
                case 0x5: subVxVy( x, y ); break;
                case 0x6: shrVxVy( x, y ); break;
                case 0x7: subnVxVy( x, y ); break;
                case 0xE: shlVxVy( x, y ); break;
                default: 
                    throw new RuntimeException( "Instruction 0x:" + 
                        Integer.toHexString( code ).toUpperCase() + 
                        " is not handled." );
                
            }
                
        } else {
            throw new RuntimeException( "Instruction starting with 0x:" + 
                Integer.toHexString( msNibble ).toUpperCase() + 
                " is not handled by XyInstructionGroup." );
        }
            
    }
    
    private void seVxVy( int x, int y ) {
        if( registers.getV( x ) == registers.getV( y ) ) {
            registers.incrementTwicePC();
        }        
    }
    
    private void sneVxVy( int x, int y ) {
        if( registers.getV( x ) != registers.getV( y ) ) {
            registers.incrementTwicePC();
        }       
    }
    

    private void ldVxVy( int x, int y ) {   
        registers.setV( x, registers.getV( y ) );
    }

    private void orVxVy( int x, int y ) {   
        registers.setV( x, registers.getV( x ) | registers.getV( y ) );
    }
    
    private void andVxVy( int x, int y ) {   
        registers.setV( x, registers.getV( x ) & registers.getV( y ) );
    }
    
    private void xorVxVy( int x, int y ) {   
        registers.setV( x, registers.getV( x ) ^ registers.getV( y ) );
    }
    
    private void addVxVy( int x, int y ) {   
        int sum = registers.getV( x ) + registers.getV( y );
        if( sum > 0xFF ) {
            registers.setV( 0xF, 1 );
        } else {
            registers.setV( 0xF, 0 );
        }
        registers.setV( x, sum & 0xFF );
    }
    
    private void subVxVy( int x, int y ) {   
        int vx = registers.getV( x );
        int vy = registers.getV( y );
        
        if( vx >= vy ) {
            registers.setV( 0xF, 1 );
        } else {
            registers.setV( 0xF, 0 );
        }
        
        registers.setV( x, (vx - vy) & 0xFF );
    }
    
    private void shrVxVy( int x, int y ) {     
        int vx = registers.getV( x );        
        registers.setV( 0xF, vx & 0x01 );
        registers.setV( x, ( vx >> 1 ) & 0xFF);
    }
    
    private void subnVxVy( int x, int y ) {
        int vx = registers.getV( x );
        int vy = registers.getV( y );
        
        if( vy >= vx ) {
            registers.setV( 0xF, 1 );
        } else {
            registers.setV( 0xF, 0 );
        }
        
        registers.setV( x, (vy - vx) & 0xFF );
            
    }
    
    private void shlVxVy( int x, int y ) {     
        int vx = registers.getV( x );
        registers.setV( 0xF, (vx >> 7) & 0x01 );
        registers.setV( x, vx << 1 );
    }     
    
}