/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.capabilities;

/**
 *
 * @author Russell Thomas
 */
import ec.util.MersenneTwisterFast;
import interaction.*;
import processes.Consumption;

public class Consuming extends Capability {
    Consumption myProcess;

    public Consuming(){
        this.myProcess = (Consumption)process;
    }
    
    public Consuming(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new Consumption(rng);
       this.myProcess = (Consumption)process;
    }

}
