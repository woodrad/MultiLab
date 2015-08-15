/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


From: https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_algorithm
    While there are input tokens left
        Read the next token from input.
        If the token is a value
            Push it onto the stack.
        Otherwise, the token is an operator (operator here includes both operators and functions).
            It is known a priori that the operator takes n arguments.
            If there are fewer than n values on the stack
                (Error) The user has not input sufficient values in the expression.
            Else, Pop the top n values from the stack.
            Evaluate the operator, with the values as arguments.
            Push the returned results, if any, back onto the stack.
    If there is only one value in the stack
        That value is the result of the calculation.
    Otherwise, there are more values in the stack
        (Error) The user input has too many values.

 */

package edu.gmu.multilab.utilities;

/**
 *
 * @author Russell Thomas
 */

import sim.models.simpleConsumption.Constants;
import sim.util.*;

public class PostfixLogic {
    Bag tokens = new Bag();
    Bag stack = new Bag();
    
    public PostfixLogic(){
        
    }
    
    public void setTokens (Bag t){
        tokens.clear();
        tokens.addAll(t);
    }
    
    public Bag getTokens (){
        return tokens;
    }
    
    public BooleanOrNAorError evaluate(){
        BooleanOrNAorError result = new BooleanOrNAorError();

        if (tokens.numObjs == 0){
            if (stack.numObjs == 1){
                if (stack.get(0)== boolean.class){
                    boolean b = (boolean)stack.get(0);
                    result.set(b);
                }
            }
        }
        return result;
    }
    
    private boolean isOperator(Object o){
        return false;  // STUB
    }

}
