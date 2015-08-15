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

import sim.util.*;

public class ResultsQuery {
   final Results result;
   final String label;
   boolean isObservable = false;
   
   public ResultsQuery(final Results r, final String l ){
       this.result = r;
       this.label = l;
       
   }
   
   public ResultsQuery(final Results r, final String l ,final boolean isObs){
       this.result = r;
       this.label = l;
       this.isObservable = isObs;
       
   }
   
   public Bag get(){
       if(!isObservable){
           if (label.equals("")){
               return result.getOutputs();
           } else {
               return result.getLabeledOutput(label);
           }
       } else {
           if (label.equals("")){
               return result.getObservables();
           } else {
               return result.getLabeledObservable(label);
           }
           
       }
   }
   

}
