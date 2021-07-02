package com.zubergu.jchip8.controller;

import static java.awt.event.KeyEvent.*;

import com.zubergu.jchip8.model.Keyboard;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardController implements KeyListener {

    private Keyboard kbd;
    
    public KeyboardController( Keyboard kbd ) {
        this.kbd = kbd;
    }
    
    @Override
    public void keyPressed( KeyEvent ev ) {
        Integer key = mapEventToChipKey( ev );
        if( key != null ) {
            kbd.setKeyState( key, true );
        }
    }
    
    @Override
    public void keyReleased( KeyEvent ev ) {
        Integer key = mapEventToChipKey( ev );
        if( key != null ) {
            kbd.setKeyState( key, false );
        }
    }
    
    @Override
    public void keyTyped( KeyEvent ev ) {
        // do nothing?
    }
    
    protected Integer mapEventToChipKey( KeyEvent ev ) {
        switch( ev.getKeyCode() ) {
            case VK_7: return 1;
            case VK_8: return 2;
            case VK_9: return 3;
            case VK_0: return 0xC;
            case VK_U: return 4;
            case VK_I: return 5;
            case VK_O: return 6;
            case VK_P: return 0xD;
            case VK_J: return 7;
            case VK_K: return 8;
            case VK_L: return 9;
            case VK_SEMICOLON: return 0xE;
            case VK_M: return 0xA;
            case VK_COMMA: return 0;
            case VK_PERIOD: return 0xB;
            case VK_SLASH: return 0xF;
            default: return null;
        }
    }
}
