/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.processes;

/**
 *
 * @author Russell Thomas
 */

import active.StoppingCondition;
import sim.util.*;
import sim.engine.*;
import ec.util.MersenneTwisterFast;
import active.*;
import space.*;
import interaction.*;

public abstract class ImplementationProcess {
    MersenneTwisterFast rng;
    LabeledBag arguments; //  = new Bag();  // parameters to the ImplementationProcess  
    Bag targets; // = new Bag();    // the operants of the process
    

    Results results; // = new Results();
    Bag rules = new Bag();
    StoppingCondition stopAt; 
    Interactor actor;
    
    
   public ImplementationProcess(){
     // TEMP COMMENT  this.arguments = new Bag();  // parameters to the ImplementationProcess
     // TEMP COMMENT   this.targets = new Bag();    // the operants of the process
    }
    
    public ImplementationProcess(final MersenneTwisterFast r){
     // TEMP COMMENT    this.arguments = new Bag();  // parameters to the ImplementationProcess
     // TEMP COMMENT    this.targets = new Bag();    // the operants of the process
        this.rng = r;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, TypedBag args){
        arguments = new LabeledBag(args);  // parameters to the ImplementationProcess
        rng = r;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, Bag targs, TypedBag args){
        arguments = new LabeledBag(args);  // parameters to the ImplementationProcess
        targets = targs;    // the operants of the process
        rng = r;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, Bag targs, TypedBag args, Results res){
        arguments = new LabeledBag(args);  // parameters to the ImplementationProcess
        targets = targs;    // the operants of the process
        rng = r;
        results = res;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, LabeledBag args){
        arguments = args;  // parameters to the ImplementationProcess
        rng = r;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, Bag targs, LabeledBag args){
        arguments = args;  // parameters to the ImplementationProcess
        targets = targs;    // the operants of the process
        rng = r;
    }
    
    public ImplementationProcess(final MersenneTwisterFast r, Bag targs, LabeledBag args, Results res){
        arguments = args;  // parameters to the ImplementationProcess
        targets = targs;    // the operants of the process
        rng = r;
        results = res;
    }
    
    public void setRNG(final MersenneTwisterFast r){
        rng = r;
    }
    
    public void setActor(Interactor a){
        actor = a;
    }
    

    private void resetOutput(Bag out){
        results.resetOutputs(out);
    }
    
    private void setOutput(Bag out){
        if (! out.equals(results.getOutputs())){
            results.clearOutputs();
            results.addAllToOutputs(out);
        }
    }
    
    public Bag getOutputs(){
       return results.getOutputs();
    }
    
    private void setResults(Results r){
        results = r;
    }
    
    public Results getResults(){
       return results;
    }
    
    private void setTargets(Bag o){
        targets = o;
    }
    
    private Bag getTargets(){
        return targets;
    }
    
    public void setArguments(LabeledBag a){
        arguments = a;
    }
    
    private LabeledBag getArguments(){
        return arguments;
    }
    
    
    private void setObservables(Bag obs){
        
            results.copyObservables(obs);

    }
    
    public void setStoppingCondition(StoppingCondition c){
        stopAt = c;
    }
    
    public boolean testStoppingCondition(){
        return false;
    }
    
    public Results execute(){  // This is a null execution process. Should be overridden in subclasses.
 
        
        // code to execute the process here!!
        // halt if testStoppingCondition() == true, or when completed
        // Then update setStatus, copyOutputs, and setObservables
        
        results.desc = "Default process...do nothing";
        results.status.setCompleted();
        results.clearOutputs();
        return results;
    }
    
    public Results execute(Bag targ, LabeledBag args) {
        setTargets(targ);
        setArguments(args);
        results = execute();
        if (stopAt != null) {
            if (stopAt.test().isTrue()) {
                results.status.setCompletedByStopCondition();

            }
        }
        return results;
    }
    
    public Results execute(Bag targ, LabeledBag args, Results r) {
        setResults(r);
        setTargets(targ);
        setArguments(args);
        results = execute();
        if (stopAt != null) {
            if (stopAt.test().isTrue()) {
                results.status.setCompletedByStopCondition();

            }
        }
        if (! isValidResult(results)){
            results.clearOutputs();
        }
        return results;
    }
    
    private boolean isValidResult(final Results r){
        boolean valid = false;
        if (r.status.isCompleted() || r.status.isCompletedByStopCondition() ){
            valid = true;
        } 
        return valid;
    }

}
