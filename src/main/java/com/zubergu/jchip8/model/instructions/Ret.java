package com.zubergu.jchip8.model.instructions;

import com.zubergu.jchip8.model.Registers;

public class Ret implements Instruction {

    private Registers registers;

    public Ret( Registers r ) {
        this.registers = r;
    }
    
    @Override
    public void execute( int code ) {
        int value = registers.popStack();
        registers.setPC( value );
    }
}
