/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.active;

import sim.util.Bag;
import utilities.*;

/**
 *
 * @author Russell Thomas
 */
public class GenericCondition implements Condition{
    String label = "";
    TestConditions testMethods = null;
    UpdateMethods  updateMethods = null;
    String testString = "";  // human readable description of the logical test
    Object lhs = new Object();
    Object rhs = new Object();
    String logicalOperator = "";
    
    public GenericCondition(){
        this.testString = "";  
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
        
    }
    public GenericCondition(String s){
        this.label = s;
        this.testString = "";  
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
     }
    
    public GenericCondition(String s, TestConditions tests){
        this.label = s;
        this.testMethods = tests;
         this.testString = "";  
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
    }
    
        public GenericCondition(String s, TestConditions tests, String tString){
        this.label = s;
        this.testMethods = tests;
        this.testString = tString; 
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
    }
    
    public GenericCondition(String s, TestConditions tests, UpdateMethods update){
        this.label = s;
        this.testMethods = tests;
        this.updateMethods = update;
        this.testString = "";  
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
     }
    
        public GenericCondition(String s, TestConditions tests, UpdateMethods update, String tString){
        this.label = s;
        this.testMethods = tests;
        this.updateMethods = update;
        this.testString = tString; 
        this.lhs = new Object();
        this.rhs = new Object();
        this.logicalOperator = "";
     }
    
    
    public BooleanOrNA test(){
        BooleanOrNA result = new BooleanOrNA();
        if (testMethods != null){
             result = testMethods.test();
        } else {
            // return NA  
        }
        return result;
    } 
    
    public BooleanOrNA test(Bag args){
        BooleanOrNA result = new BooleanOrNA();
        if (testMethods != null){
             result = testMethods.test(args);
        } else {
            // return NA  
        }
        return result;
    }
    
    public BooleanOrNA test(StateVariables v){
        BooleanOrNA result = new BooleanOrNA();
        if (testMethods != null){
             result = testMethods.test(v);
        } else {
            // return NA  
        }
        return result;
    }
    
    public void update(Bag values){
        if (updateMethods != null){
             updateMethods.update(values);
        } else {
             // soft error - return without updating 
        }
    }
    
    public void update(){
        if (updateMethods != null){
             updateMethods.update();
        } else {
            // soft error - return without updating 
        }
    }
    
    public void setLabel(String s){
        label = s;
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setTestString(String s){
        testString = s;
    }
    
    public String getTestString(){
        return testString;
    }
    
    public void setLHS(Object o){
        lhs = o;
    }
    public Object getLHS(){
        return lhs;
    }
    
    public void setRHS(Object o){
        rhs = o;
    }
    
    public Object getRHS(){
        return rhs;
    }
    
    public void setLogicalOperator(String op){
        logicalOperator = op;
    }
    public Object getLogicalOperator(){
        return logicalOperator;
    }

}
