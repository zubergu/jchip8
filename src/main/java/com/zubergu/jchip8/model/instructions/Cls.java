package com.zubergu.jchip8.model.instructions;

import com.zubergu.jchip8.model.Display;

public class Cls implements Instruction {

    private Display display;

    public Cls( Display d ) {
        this.display = d;
    }
    
    @Override
    public void execute( int code ) {
        for( int i = 0; i < Display.NUMBER_OF_ROWS; i++ ) {
            for( int j = 0; j < Display.NUMBER_OF_COLUMNS; j++ ) {
                display.setPixel( j, i, false ); // first column, then row
            }
        }
    }
}