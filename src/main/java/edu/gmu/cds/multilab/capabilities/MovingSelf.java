/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.capabilities;

import ec.util.MersenneTwisterFast;
import processes.*;

/**
 *
 * @author Russell Thomas
 */
public class MovingSelf extends Capability {
    
        MovingSelfProcess myProcess;

    public MovingSelf(){
        this.myProcess = (MovingSelfProcess)process;
    }
    
    public MovingSelf(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new MovingSelfProcess(rng);
       this.process.setActor(getActor());
       this.myProcess = (MovingSelfProcess)process;
    }

}
