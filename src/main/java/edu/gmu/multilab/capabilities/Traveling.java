/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.capabilities;

import ec.util.MersenneTwisterFast;
import processes.TravelingProcess;

/**
 *
 * @author Russell Thomas
 */
public class Traveling extends Capability {
    TravelingProcess myProcess;

    public Traveling(){
        this.myProcess = (TravelingProcess)process;
    }
    
    public Traveling(final MersenneTwisterFast r){
       this.myClass = Consuming.class;
       super.rng = r;
       this.process = new TravelingProcess(rng);
       this.myProcess = (TravelingProcess)process;
    }
}
