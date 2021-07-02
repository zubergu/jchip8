package com.zubergu.jchip8.model;

import com.zubergu.jchip8.model.instructions.*;
import java.util.Map;
import java.util.HashMap;

public class Chip {
    private Memory memory;
    private Display display;
    private Keyboard keyboard;
    private Registers registers;
    private Map<InstructionGroup, Instruction> instructionSet;
    
    private enum InstructionGroup {
        CLS,
        RET,
        NNN,
        XKK,
        XY,
        XYN,
        EX_FX;
    }
    
    
    public Chip( Memory m, Display d, Keyboard k, Registers r ) {
        memory = m;
        display = d;
        keyboard = k;
        registers = r;
        initializeInstructionSet();
    }
    
    
    public void executeCycle() {
        int instructionCode = fetch();
        System.out.println( "Instruction: 0x" + Integer.toHexString( instructionCode ).toUpperCase() );
        System.out.println( "PC: 0x" + Integer.toHexString( registers.getPC() ).toUpperCase() ); 
        Instruction instruction = decode( instructionCode );
        registers.incrementTwicePC();
        System.out.println( "PC: 0x" + Integer.toHexString( registers.getPC() ).toUpperCase() ); 
        instruction.execute( instructionCode );
        System.out.println( "PC after execution: 0x" + Integer.toHexString( registers.getPC() ).toUpperCase() ); 
    }
    
    
    private int fetch() {
        return memory.read16Bit( registers.getPC() );
    }
    
    public Instruction decode( int instruction ) {
        int msNibble = instruction >> 12; // instruction most significant nibble
        int lsNibble = instruction & 0x0F;// instruction least significant nibble
        
        if( instruction == 0x00E0 ) {
            return instructionSet.get( InstructionGroup.CLS );
        } else if ( instruction == 0x00EE ) {
            return instructionSet.get( InstructionGroup.RET );
        }
        
        switch ( msNibble ) {
            case 0x0:
            case 0x1: 
            case 0x2:
            case 0xA:
            case 0xB: return instructionSet.get( InstructionGroup.NNN );
            case 0x3:
            case 0x4:
            case 0x6:
            case 0x7:
            case 0xC: return instructionSet.get( InstructionGroup.XKK );
            case 0x5:
            case 0x8:
            case 0x9: return instructionSet.get( InstructionGroup.XY );
            case 0xD: return instructionSet.get( InstructionGroup.XYN );
            case 0xE:
            case 0xF: return instructionSet.get( InstructionGroup.EX_FX );
            default: throw new RuntimeException( "Failed to decode 0x:" + Integer.toHexString( instruction ) );                     
        }
    }
    
    private void initializeInstructionSet() {
        instructionSet = new HashMap<InstructionGroup, Instruction>();
        instructionSet.put( InstructionGroup.CLS, new Cls( display ) );
        instructionSet.put( InstructionGroup.RET, new Ret( registers ) );
        instructionSet.put( InstructionGroup.XY, new XyInstructionGroup( registers ) );
        instructionSet.put( InstructionGroup.XYN, new DrwVxVyNibble( display, registers, memory ) );
        instructionSet.put( InstructionGroup.EX_FX, new ExFxInstructionGroup( registers, keyboard, memory) );
        instructionSet.put( InstructionGroup.NNN, new NnnInstructionGroup( registers ) );
        instructionSet.put( InstructionGroup.XKK, new XkkInstructionGroup( registers ) );
        
    }
    
    
}
