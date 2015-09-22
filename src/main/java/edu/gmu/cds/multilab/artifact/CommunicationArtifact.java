/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.artifact;

import sim.engine.SimState;

/**
 *
 * @author Russell Thomas
 */
public class CommunicationArtifact extends Artifact {
    private static final long serialVersionUID = 14;
    
    public CommunicationArtifact(){
        
    }
    public CommunicationArtifact(final SimState state){
        super.setRNG(state.random);

    }
    
    public CommunicationArtifact(final SimState state, int serial){  
        super.setRNG(state.random);
        setID(serial);
    }
}
