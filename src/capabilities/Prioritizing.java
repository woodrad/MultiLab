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
import sim.util.*;
import java.util.Comparator;
import processes.*;

public class Prioritizing extends Capability {

    
    Prioritization myProcess;

    public Prioritizing(){
        this.myProcess = (Prioritization)process;
    }
    
    public Prioritizing(final MersenneTwisterFast r){
       this.myClass = this.getClass();
       this.rng = r;
       this.process = new Prioritization(rng);
       this.myProcess = (Prioritization)process;
    }

}
