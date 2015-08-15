/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.active;

/**
 *
 * @author Russell Thomas
 */

import utilities.*;
import sim.util.*;

public class StoppingCondition {
    
    Condition condition;
    StateVariables stateVariables = new StateVariables();
    
   // constructor
   public StoppingCondition(Condition c){
        condition = c;
    }
   
   public void set(Condition c){
       condition = c;     
       
   }
  
   
   public BooleanOrNA test(){
       if (stateVariables.numObjs == 0){
           return condition.test();
       } else {
            return condition.test(stateVariables);
       }
       
   }
   
   public BooleanOrNA test(Bag args){
       return condition.test(args);
   }
   
   public void setStateVariables(Bag names, Bag types, Bag values){
       StateVariables s = new StateVariables(names, types, values);
       stateVariables = s;
   }
   
   public StateVariables getStateVariables(){
       return stateVariables;
   }
   
   public String getTestString(){
       return condition.getTestString();
   }

}
