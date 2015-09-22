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

import processes.*;
import ec.util.MersenneTwisterFast;
import interaction.*;

public abstract class Capability {
    ImplementationProcess process;
    MersenneTwisterFast rng;
    Class myClass;
    Interactor actor;
    
    public Capability(){
        this.myClass = Capability.class;
        
    }
    public Capability(ImplementationProcess p){
        this.myClass = Capability.class;
        process = p;
    }
    
    public Capability(final MersenneTwisterFast r){
       this.myClass = Capability.class;
       rng = r;
    }
    
    public Capability(ImplementationProcess p, final MersenneTwisterFast r){
       this.myClass = Capability.class;
       rng = r;
       process = p;
    }
    
    public void setProcess(ImplementationProcess p){
        process = p;
    }
    
    public ImplementationProcess getProcess(){
        return process;
    }
    
    public void setActor(Interactor a){
        actor = a;
    }
    
    public Interactor getActor(){
        return actor;
    }
    
    
    
    public void setRNG(final MersenneTwisterFast r){
        rng = r;
    }

}
