/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package artifact;

import sim.engine.SimState;

/**
 *
 * @author Russell Thomas
 */
public class DesignArtifact extends Artifact {
    private static final long serialVersionUID = 16; 
    public DesignArtifact(){
        
    }
    public DesignArtifact(final SimState state){
        super.setRNG(state.random);

    }
    
    public DesignArtifact(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
    }

}
