/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.processes;

import active.Results;
import ec.util.MersenneTwisterFast;

/**
 *
 * @author Russell Thomas
 */
public class SortProcess extends ImplementationProcess{
    
    public SortProcess(MersenneTwisterFast r) {
        super.rng = r;
    }
    
    @Override
    public Results execute(){  // this is a stub for now.
        results.desc = "Sort....";
        results.status.setCompleted();
        return results;
    }

}
