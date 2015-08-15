/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.space;

import affordances.*;
import sim.util.*;
import utilities.*;
import active.*;

/**
 *
 * @author Russell Thomas
 */
public class ScoredBag extends TypedBag implements EngageSearchable, EngageSortable {
    DoubleBag scores;
    final NumericalComparator compare = new NumericalComparator();
    final BagMethods bm = new BagMethods();
    
    public ScoredBag (final Class t, final Bag b, final DoubleBag s){
        setType(t );
        this.bag = b;
        this.scores = s;
    }
    
    public ScoredBag(final Class t, Bag b){
        setType(t );
        for (int i = 0; i < b.numObjs; i++){
            ScoredObject so = (ScoredObject)b.get(i);
            bag.add(so.getObject());
            scores.add(so.getScore());
        }
    }
    
    public void add(ScoredObject so){
        bag.add(so.getObject());
        scores.add(so.getScore()); 
    }
    
    public void sort(boolean ascending){
        
        IntBag sortOrd = bm.sortOrder(scores);
        Bag sorted = new Bag(bag.numObjs);
        for (int i = 0; i < bag.numObjs; i++){
            sorted.set(sortOrd.get(id), bag.get(i));
        }
        bag.clear();
        bag.addAll(sorted);
        if (! ascending){
            bag.reverse();
            scores.reverse();
        }
    }
    
    @Override
    public Results get(String name){
        Results result = new Results();
        double d = Double.parseDouble(name);
        int pos = bm.position(scores, d);
        if (pos >= 0){
            result.addToOutputs(bag.get(pos));
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result;
    }
    
    @Override
    public Results set(String name, Object obj) {
        Results result = new Results();
        if (this.getType().isAssignableFrom(obj.getClass())) {
            double d = Double.parseDouble(name);
            int pos = bm.position(scores, d);
            if (pos >= 0) {
                bag.set(pos, obj);
                result.status.setCompleted();
            } else {
                result.status.setNotCompleted();
            }
        } else {
            result.status.setError();
        }
        return result;
    }
    
    public Results get(double s){
        Results result = new Results();
        int pos = bm.position(scores, s);
        if (pos >= 0){
            result.addToOutputs(bag.get(pos));
            result.status.setCompleted();
        } else {
            result.status.setNotCompleted();
        }
        return result;
    }
    
    public Results set(double s, Object obj) {
        Results result = new Results();
        if (this.getType().isAssignableFrom(obj.getClass())) {
            int pos = bm.position(scores, s);
            if (pos >= 0) {
                bag.set(pos, obj);
                result.status.setCompleted();
            } else {
                result.status.setNotCompleted();
            }
        } else {
            result.status.setError();
        }
        return result;
    }
    
    @Override
    public void initializeAffordances(){
        super.initializeAffordances();
        Searchable search = new Searchable();
        Sortable sort = new Sortable();
        Bag afford = new Bag();
        afford.add(search);
        afford.add(sort);
        this.addToMyAffordances(afford);
    }

}
