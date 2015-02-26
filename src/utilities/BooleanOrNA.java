/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import sim.models.simpleConsumption.Constants;

/**
 *
 * @author Russell Thomas
 */
public class BooleanOrNA {
    int value = Constants.NA;
    
    public BooleanOrNA(){
        // value is initially NA
    }
    public BooleanOrNA(int v){
        if (v == Constants.NA){
            value = Constants.NA;
        } else if (v == Constants.TRUE){
            value = Constants.TRUE;
        } else  if (v == Constants.FALSE){
            value = Constants.FALSE;
        }
        // if v doesn't match these constants, fail silently, leaving value == NA
    }
    
    public boolean isTrue(){
        return value == Constants.TRUE;
    }
    
    public boolean isTrueOrNA(){
        return value == Constants.TRUE || value == Constants.NA;
    }
    
    public boolean isFalseOrNA(){
        return value == Constants.FALSE || value == Constants.NA;
    }
    
    public boolean isFalse(){
        return value == Constants.FALSE;
    }
    
    public boolean isNA(){
        return value == Constants.NA;
    }
    
    public void setFalse(){
        value = Constants.FALSE;
    }
    
    public void setNA(){
        value = Constants.NA;
    }
    
    public void setTrue(){
        value = Constants.TRUE;
    }
    
    public void set(boolean b){
        if (b){
            value = Constants.TRUE;
        } else {
            value = Constants.FALSE;
        }
    }

}
