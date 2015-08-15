/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.characteristics;

/**
 *
 * @author Russell Thomas
 */
public abstract class Characteristic {
    
    String label;
    Object value;
    
    public void setLabel(String l){
        label = l;
    }
    
    public String getLabel(){
        return label;
    }
    
    public boolean doesMatchLabel(String l){
        return label.equals(l);
    }
    
    public Object getValue(){
        return value;
    }
    
    public void setValue(Object o){
        value = o;
    }

}
