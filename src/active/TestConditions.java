/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package active;

import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
import utilities.*;

public interface TestConditions {
    
    public BooleanOrNA test(); 
    
    public BooleanOrNA test(Bag args);
    
    public BooleanOrNA test(StateVariables v);

}
