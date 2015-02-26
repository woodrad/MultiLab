/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capabilities;

/**
 *
 * @author Russell Thomas
 */
import ec.util.MersenneTwisterFast;
import interaction.*;
import processes.*;

public class ActRandomly extends Capability {
    
    ActRandomlyProcess myProcess;

    public ActRandomly(){
        this.myProcess = (ActRandomlyProcess)process;
    }
    
    public ActRandomly(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       super.rng = r;
       this.process = new ActRandomlyProcess(rng);
       this.myProcess = (ActRandomlyProcess)process;
    }

}
