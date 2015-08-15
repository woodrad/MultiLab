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
public class TravelingProcess extends ImplementationProcess {
    
    public TravelingProcess(MersenneTwisterFast r) {
        super.rng = r;
    }
    
    @Override
    public Results execute(){  // this is a stub for now.  
        Results result = new Results();
        result.desc = "Traveling....";
        result.status.setCompleted();
        return result;
    }

}
