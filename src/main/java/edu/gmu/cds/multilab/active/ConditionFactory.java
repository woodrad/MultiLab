/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.active;

import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public class ConditionFactory {
    
    
    public ConditionFactory(){
        
    }
    
    // build genric Conditions -- Factory not really needed for these, but included for completeness
    public Condition build(String n){
        GenericCondition c = new GenericCondition(n);
        return c;
    }
    
    public Condition build(String n, TestConditions tests){
        GenericCondition c = new GenericCondition(n, tests);
        return c;
    }
    
    public Condition build(String n, TestConditions tests, String tString){
        GenericCondition c = new GenericCondition(n, tests, tString);
        return c;
    }
    
    public Condition build(String n, TestConditions tests,UpdateMethods updates){
        GenericCondition c = new GenericCondition(n, tests, updates);
        return c;
    }
    
    public Condition build(String n, TestConditions tests,UpdateMethods updates, String tString ){
        GenericCondition c = new GenericCondition(n, tests, updates, tString);
        return c;
    }
    
    // build preconfigured Conditions
    
   

}
