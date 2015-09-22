/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.capabilities;

import ec.util.MersenneTwisterFast;
import processes.SortProcess;

/**
 *
 * @author Russell Thomas
 */
public class Sorting extends Capability {
    
    SortProcess myProcess;

    public Sorting(){
        this.myProcess = (SortProcess)process;
    }
    
    public Sorting(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new SortProcess(rng);
       this.myProcess = (SortProcess)process;
    }

}
