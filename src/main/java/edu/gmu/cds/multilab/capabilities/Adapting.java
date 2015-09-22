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
public class Adapting extends Capability {
    Adaptation myProcess;

    public Adapting(){
        this.myProcess = (Adaptation)process;
    }
    
    public Adapting(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new Adaptation(rng);
       this.myProcess = (Adaptation)process;
    }
}
