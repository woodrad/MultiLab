/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.active;

/**
 *
 * @author Russell Thomas
 */

import sim.util.*;
import edu.gmu.cds.multilab.utilities.*;

public interface Condition { 
    /*
    String label = "";
    String testString = "";
    Object lhs = new Object();
    Object rhs = new Object();
    String logicalOperator = "";
    */
    
    public BooleanOrNA test();  
    
    public BooleanOrNA test(Bag args);
    
    public BooleanOrNA test(StateVariables v);
    
    public void update(Bag values);
    
    public void update();
    
    public void setLabel(String s);
    public String getLabel();
    
    public void setTestString(String s);
    public String getTestString();
    
    public void setLHS(Object o);
    public Object getLHS();
    
    public void setRHS(Object o);
    public Object getRHS();
    
    public void setLogicalOperator(String op);
    public Object getLogicalOperator();

}
