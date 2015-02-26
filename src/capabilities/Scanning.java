/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capabilities;

import ec.util.MersenneTwisterFast;;
import processes.*;
import sim.util.*;

/**
 *
 * @author Russell Thomas
 */

import sim.models.simpleConsumption.Constants;

public class Scanning extends Capability {
    
        
    ScanningProcess myProcess;

    public Scanning(){
        this.myProcess = (ScanningProcess)process;
    }
    
    public Scanning(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new ScanningProcess(rng);
       this.myProcess = (ScanningProcess)process;
       this.myProcess.setFocus(new Int2D(Constants.GRID_WIDTH / 2 ,Constants.GRID_HEIGHT / 2));  // STUB 
       this.myProcess.setScanLimit(30);                                                         // STUB 
    }

}
