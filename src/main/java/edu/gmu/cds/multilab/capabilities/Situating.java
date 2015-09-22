/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.capabilities;

import ec.util.MersenneTwisterFast;
import processes.SituationProcess;

/**
 *
 * @author Russell Thomas
 */
public class Situating  extends Capability {
    SituationProcess myProcess;

    public Situating(){
        this.myProcess = (SituationProcess)process;
    }
    
    public Situating(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new SituationProcess(rng);
       this.myProcess = (SituationProcess)process;
    }

}
