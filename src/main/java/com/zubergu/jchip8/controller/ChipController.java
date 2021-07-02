package com.zubergu.jchip8.controller;

import com.zubergu.jchip8.model.Chip;
import com.zubergu.jchip8.model.Display;
import com.zubergu.jchip8.model.Memory;
import com.zubergu.jchip8.model.Registers;
import com.zubergu.jchip8.model.Keyboard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import java.io.File;
import java.io.FileInputStream;


public class ChipController implements ActionListener {
    
    private Chip chip;
    private Display display;
    private Memory memory;
    private Registers registers;
    private Keyboard keyboard;
    
    private int delay = 1000; // default
    private int delayCountForSTandDT;
    private int cycleCount = 0;
    
    private JFileChooser fileChooser = new JFileChooser();
    private Timer mainTimer;
    
    
    public ChipController( Chip c, Display d, Memory m, Registers r, Keyboard k ) {
        chip = c;
        display = d;
        memory = m;
        registers = r;
        keyboard = k;
        mainTimer = new Timer( delay, this );
        mainTimer.setActionCommand( "MainTimer" );
    }
    
    public void setFrequency( int freq ) {
        // T = 1/f
        delay = 1000 / freq; // Hz -> miliseconds
        
        // calculate how many main timer triggers should trigger ST and DT decrement
        // if main frequency is lower than 60 expected for DT and ST then 
        // set count to 1, so they trigger with lower main freq, not 60 Hz
        
        delayCountForSTandDT = freq / 60;
        if( delayCountForSTandDT == 0 ) {
            delayCountForSTandDT = 1;  
        }
        mainTimer.setDelay( delay );
    }
    
    @Override
    public void actionPerformed( ActionEvent ev ) {
        switch( ev.getActionCommand() ) {
            case "Load": handleLoadAction( ev ); break;
            case "Start": handleStartAction( ev ); break;
            case "Stop": handleStopAction( ev ); break;
            case "MainTimer": handleMainTimerEvent( ev ); break;
            default:
                throw new RuntimeException( "Unhandled action " + ev.getActionCommand() + " in ChipController." );
                
        }
    }
    
    private void handleLoadAction( ActionEvent ev ) {
        int returnValue = fileChooser.showOpenDialog( (Component) ev.getSource() );
        
        if( returnValue == JFileChooser.APPROVE_OPTION ) {
            File file = fileChooser.getSelectedFile();
            try {
                loadProgramFromFile( file );
                registers.setPC( Memory.PROGRAM_BASE ); // reset PC when loading file
                JOptionPane.showMessageDialog( (Component) ev.getSource(),
                    "Program loaded succesfully",
                    "Load",
                    JOptionPane.PLAIN_MESSAGE );
            } catch( Exception ex ) {
                JOptionPane.showMessageDialog( (Component) ev.getSource(),
                    ex.getMessage(),
                    "Load",
                    JOptionPane.ERROR_MESSAGE );
            }
        }
    }
    
    private void handleStartAction( ActionEvent ev ) {
        JMenuItem source = (JMenuItem) ev.getSource();
        source.setText( "Stop" );
        mainTimer.start();
    }
    
    private void handleStopAction( ActionEvent ev ) {
        JMenuItem source = (JMenuItem) ev.getSource();
        source.setText( "Start" );
        
        mainTimer.stop();
    }
    
    private void handleMainTimerEvent( ActionEvent ev ) {
        // execution of instrucion ( fetch, decode, execute, step PC )
        chip.executeCycle();
        cycleCount++;
        if( cycleCount == delayCountForSTandDT ) {
            registers.decrementDT();
            registers.decrementST();
            cycleCount = 0;
        }
    }
    
    
    private void loadProgramFromFile( File file ) throws Exception {
        if( file.length() > Memory.MAX_PROGRAM_SIZE ) {
            throw new Exception( "File exceeds max program size of " + Memory.MAX_PROGRAM_SIZE );

        } else {
            try ( FileInputStream fis = new FileInputStream( file ) ) {
                int val = 0;
                int count = 0;
                while( ( val = fis.read() ) != -1 ) {
                    memory.write8Bit( count + Memory.PROGRAM_BASE, val );
                    System.out.printf( "ADDR: 0x%s == 0x%s\n", 
                        Integer.toHexString( count + Memory.PROGRAM_BASE ),
                        Integer.toHexString( val ) );
                    count++;
                } 
            } catch ( Exception ex ) {
                throw new Exception( "Exception happened when loading file to memory." );                
            }
        }
    }
    
}
