/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.artifact;

/**
 *
 * @author Russell Thomas
 */

import sim.util.*;
import sim.engine.*;
import interaction.*;

public abstract class Artifact extends Interactor  {
    
    
    // constructors
    public Artifact(){
        
    }
    public Artifact(final SimState state){
        super.setRNG(state.random);

    }
    
    public Artifact(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
    }
    
    

}
