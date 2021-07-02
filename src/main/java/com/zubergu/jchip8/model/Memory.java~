package com.zubergu.jchip8.model;

import java.io.File;

public class Memory {
    
    public static final int FONT_BASE = 0;
    public static final int DEFAULT_MEMORY_SIZE = 4096;
    public static final int PROGRAM_BASE = 512;
    public static final int MAX_PROGRAM_SIZE = DEFAULT_MEMORY_SIZE - PROGRAM_BASE;
    
    private int[] ram;
    
    public Memory() {
        this( DEFAULT_MEMORY_SIZE );
    }
    
    public Memory( int memorySize ) {
        ram = new int[ memorySize ];
    }
    
    
    public int read8Bit( int address ) {
        return ram[address];                              
    }
    
    public int read16Bit( int address ) {
        int nibbleH = ram[address] << 8;
        int nibbleL = ram[address+1]; // to preserve bit pattern when conversion to int hits
        return (nibbleH | nibbleL);
    }
    
    public void write8Bit( int address, int value ) {
        if( value < 0 || value > 0xFF ) {
            throw new RuntimeException( "Value 0x" + Integer.toHexString( value ) + " too big for byte." );
        }
        ram[address] = value;
    }
    
    public void write16Bit( int address, int value ) {
        if( value < 0 || value > 0xFFFF ) {
            throw new RuntimeException( "Value 0x" + Integer.toHexString( value ) + " too big for 2 bytes." );
        }
        ram[address] =   value >> 8 ;
        ram[address+1] = value & 0xFF;
    }
    
    public void initializeFont() {
        ram[0 + FONT_BASE] = 0xF0;  // **** 
        ram[1 + FONT_BASE] = 0x90;  // *  *
        ram[2 + FONT_BASE] = 0x90;  // *  *
        ram[3 + FONT_BASE] = 0x90;  // *  *
        ram[4 + FONT_BASE] = 0xF0;  // ****
        
        ram[5 + FONT_BASE] = 0x20;  //   *
        ram[6 + FONT_BASE] = 0x60;  //  **
        ram[7 + FONT_BASE] = 0x20;  //   *
        ram[8 + FONT_BASE] = 0x20;  //   *
        ram[9 + FONT_BASE] = 0x70;  //  ***
        
        ram[10 + FONT_BASE] = 0xF0; // ****
        ram[11 + FONT_BASE] = 0x10; //    *
        ram[12 + FONT_BASE] = 0xF0; // ****
        ram[13 + FONT_BASE] = 0x80; // *
        ram[14 + FONT_BASE] = 0xF0; // ****
        
        ram[15 + FONT_BASE] = 0xF0; // ****
        ram[16 + FONT_BASE] = 0x10; //    *
        ram[17 + FONT_BASE] = 0xF0; // ****
        ram[18 + FONT_BASE] = 0x10; //    *
        ram[19 + FONT_BASE] = 0xF0; // ****
        
        ram[20 + FONT_BASE] = 0x90; // *  *
        ram[21 + FONT_BASE] = 0x90; // *  *
        ram[22 + FONT_BASE] = 0xF0; // ****
        ram[23 + FONT_BASE] = 0x10; //    *
        ram[24 + FONT_BASE] = 0x10; //    *
        
        ram[25 + FONT_BASE] = 0xF0; // ****
        ram[26 + FONT_BASE] = 0x80; // *
        ram[27 + FONT_BASE] = 0xF0; // ****
        ram[28 + FONT_BASE] = 0x10; //    *
        ram[29 + FONT_BASE] = 0xF0; // ****
        
        ram[30 + FONT_BASE] = 0xF0; // ****
        ram[31 + FONT_BASE] = 0x80; // *  
        ram[32 + FONT_BASE] = 0xF0; // ****
        ram[33 + FONT_BASE] = 0x90; // *  *
        ram[34 + FONT_BASE] = 0xF0; // ****
        
        ram[35 + FONT_BASE] = 0xF0; // ****
        ram[36 + FONT_BASE] = 0x10; //    *
        ram[37 + FONT_BASE] = 0x20; //   *
        ram[38 + FONT_BASE] = 0x40; //  *
        ram[39 + FONT_BASE] = 0x40; //  *
        
        ram[40 + FONT_BASE] = 0xF0; // ****
        ram[41 + FONT_BASE] = 0x90; // *  *
        ram[42 + FONT_BASE] = 0xF0; // ****
        ram[43 + FONT_BASE] = 0x90; // *  *
        ram[44 + FONT_BASE] = 0xF0; // ****
        
        ram[45 + FONT_BASE] = 0xF0; // ****
        ram[46 + FONT_BASE] = 0x90; // *  *
        ram[47 + FONT_BASE] = 0xF0; // ****
        ram[48 + FONT_BASE] = 0x10; //    *
        ram[49 + FONT_BASE] = 0xF0; // ****
        
        ram[50 + FONT_BASE] = 0xF0; // ****
        ram[51 + FONT_BASE] = 0x90; // *  *
        ram[52 + FONT_BASE] = 0xF0; // ****
        ram[53 + FONT_BASE] = 0x90; // *  *
        ram[54 + FONT_BASE] = 0x90; // *  *
        
        ram[55 + FONT_BASE] = 0xE0; // ***
        ram[56 + FONT_BASE] = 0x90; // *  *
        ram[57 + FONT_BASE] = 0xE0; // ***
        ram[58 + FONT_BASE] = 0x90; // *  *
        ram[59 + FONT_BASE] = 0xE0; // ***
        
        ram[60 + FONT_BASE] = 0xF0; // ****
        ram[61 + FONT_BASE] = 0x80; // *  
        ram[62 + FONT_BASE] = 0x80; // *
        ram[63 + FONT_BASE] = 0x80; // *  
        ram[64 + FONT_BASE] = 0xF0; // ****
        
        ram[65 + FONT_BASE] = 0xE0; // ***
        ram[66 + FONT_BASE] = 0x90; // *  *
        ram[67 + FONT_BASE] = 0x90; // *  *
        ram[68 + FONT_BASE] = 0x90; // *  *
        ram[69 + FONT_BASE] = 0xE0; // ***
        
        ram[70 + FONT_BASE] = 0xF0; // ****
        ram[71 + FONT_BASE] = 0x80; // *  
        ram[72 + FONT_BASE] = 0xF0; // ****
        ram[73 + FONT_BASE] = 0x80; // *  
        ram[74 + FONT_BASE] = 0xF0; // ****
        
        ram[75 + FONT_BASE] = 0xF0; // ****
        ram[76 + FONT_BASE] = 0x80; // *  
        ram[77 + FONT_BASE] = 0xF0; // ****
        ram[78 + FONT_BASE] = 0x80; // *
        ram[79 + FONT_BASE] = 0x80; // *
    }
    
}
