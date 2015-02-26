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
import sim.util.*;
import active.*;

public class LabeledBag extends TypedBag implements EngageSearchable {
    Bag labels;
    
    public LabeledBag(){
        setType(Object.class);
        labels = new Bag();
        bag = new Bag();
    }

    public LabeledBag (final Class t, final Bag cbag, final Bag clabels){
        setType(t );
        bag = cbag;
        labels = clabels;
    }
    
    public LabeledBag(LabeledBag b){
        labels = new Bag();
        bag = new Bag();
        labels.addAll(b.getLabels());
        bag.addAll(b.getBag());
        setType(b.getType());
    }
    
    public LabeledBag(TypedBag b){
        labels = new Bag();
        for (int i = 0; i < b.getNumObjs(); i++){
            labels.add("");
        }
        bag = new Bag();
        setType(b.getType());
        if (b.getBag() != null){
            bag.addAll(b.getBag());
        }
    }
    
    public void add(String l, Object obj){
        labels.add(l);
        bag.add(obj);
    }
    
    public Results addAll(LabeledBag b){
        Results result = new Results();
        if (this.getType().isAssignableFrom(b.getType()) ){
            labels.addAll(b.getLabels());
            bag.addAll(b.getBag());
            result.status.setCompleted();
        } else {
            result.status.setError();
        }
        return result;
    }
    
    @Override
    public void clear(){
        labels.clear();
        bag.clear();
    }
    
    
    @Override
    public Results get(String name){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < labels.numObjs; i ++){
           String label = (String)labels.get(i);
           if (label.equals(name)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            Object obj = bag.get(index);
            if (obj.getClass() == Bag.class){
                result.addAllToOutputs((Bag)obj );
            } else {
                result.addToOutputs(obj );
            }
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result;   
    }
    
    public Results set(String name, Object obj){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < labels.numObjs; i ++){
           String label = (String)labels.get(i);
           if (label.equals(name)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            bag.set(index,obj);
            result.status.setCompleted();
        } else {
            bag.add(obj);
            labels.add(name);
            result.status.setCompleted();
        }
        return result;
    }
    
   /* THIS IS NOT RIGHT "addAll" -- REMOVE
    public Results set(String name, Bag b){
        Results result = new Results();
        int index = -1;
        for (int i = 0; i < labels.numObjs; i ++){
           String label = (String)labels.get(i);
           if (label.equals(name)){
               index = i;
               break;
           }
        }
        if (index >= 0){
            bag.set(index,b);
            result.status.setCompleted();
        } else {
            bag.addAll(b);
            labels.add(name);
            result.status.setCompleted();
        }
        return result;
    }
    */
    
    public Bag getLabels(){
        return labels;
    }
    
    @Override
    public void initializeAffordances(){
        super.initializeAffordances();
        Searchable s = new Searchable();
        Bag afford = new Bag();
        afford.add(s);
        this.addToMyAffordances(afford);
    }

}
