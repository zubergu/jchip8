package com.zubergu.jchip8;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import com.zubergu.jchip8.view.*;
import com.zubergu.jchip8.model.*;
import com.zubergu.jchip8.controller.*;

public class AppStart {
    
    public static void main( String[] args ) {
         
        SwingUtilities.invokeLater( new Runnable() {
                @Override
                public void run() {
                    // model
                    Display display = new Display();
                    Keyboard keyboard = new Keyboard();
                    Memory memory = new Memory();
                    memory.initializeFont();
                    Registers registers = new Registers();
                    Chip chip = new Chip( memory, display, keyboard, registers );
                    
                    // view
                    MainScreen mainScreen = new MainScreen();
                    DisplayScreen displayScreen = new DisplayScreen( display );
                    
                    // controller
                    ChipController chipController = new ChipController( chip, display, memory, registers, keyboard );
                    chipController.setFrequency( 1000 ); 
                    KeyboardController keyboardController = new KeyboardController( keyboard );                   
                    
                    mainScreen.addKeyListener( keyboardController );
                    mainScreen.add( displayScreen, BorderLayout.CENTER );
                    
                    mainScreen.getLoadFileItem().addActionListener( chipController );
                    mainScreen.getStartStopItem().addActionListener( chipController );
                    display.addObserver( displayScreen );
                    mainScreen.pack();
                    mainScreen.setVisible( true );
                }
        } );
        
    }
    
}
