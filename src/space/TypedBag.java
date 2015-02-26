/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package space;

/**
 *
 * @author Russell Thomas
 */

import affordances.*;
import interaction.*;
import sim.util.*;
import active.*;

public class TypedBag extends Interactor implements EngageTypedSpace, EngageScannable{
    Bag bag;
    private int numObjs = 0;
    
    
    
    public TypedBag(){
        
    }
    
    public TypedBag(final Class t){
        setType(t );
        bag = new Bag();
        
    }
    public TypedBag(final Class t, final Bag b){
        setType(t );
        bag = b;
        numObjs = b.numObjs;
    }
    
    public Bag getBag(){
        return bag;
    }
    
    public void clear(){
        bag.clear();
        numObjs = 0;
    }
    
    public Object get(int i){
        return bag.get(i);
    }
    
    public Results set(int i, Object obj){
        Results result = new Results();
        if (this.getType().isAssignableFrom(obj.getClass())) {
            bag.set(i,obj);
            numObjs = bag.numObjs;
            result.status.setCompleted();
        } else {
            result.status.setError();
        }
        return result;
    }
    
    public Results addAll(TypedBag b){
        Results result = new Results();
        if (this.getType().isAssignableFrom(b.getType())) {
            bag.addAll(b.getBag());
            result.status.setCompleted();
            numObjs = bag.numObjs;
        } else {
            result.status.setError();
        }
        return result;
    }
    
    public Results add(Object obj){
        Results result = new Results();
        if (this.getType().isAssignableFrom(obj.getClass())) {
            bag.add(obj);
            numObjs++;
            result.status.setCompleted();
        } else {
            result.status.setError();
        }
        return result;
    }
    
    public void initializeAffordances(){
        Scannable s = new Scannable();
        Bag afford = new Bag();
        afford.add(s);
        this.addToMyAffordances(afford);
    }
    
    public int getNumObjs(){
        if (bag != null){
            numObjs = bag.numObjs;  // update this value to current contents of bag
        } else {
            numObjs = 0;
        }
        return numObjs;
    }

}
