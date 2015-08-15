/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.utilities;

import sim.util.Bag;
import sim.util.DoubleBag;
import sim.util.IntBag;

/**
 *
 * @author Russell Thomas
 */
public class BagMethods {
    public BagMethods(){  // constructor
        
    }
    
    public boolean containsClass(Bag b, Class c){
        boolean test = false;
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (o.getClass() == c){
                test = true;
                break;
            }
        }
        return test;
    }
    
    public Bag deduplicate(Bag b){
        Bag d = new Bag();
        
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (!d.contains(o)){
                d.add(o);
            }
        }
        
        return d;
    }
    
    public IntBag deduplicate(IntBag b){
        IntBag d = new IntBag();
        
        for (int i = 0; i < b.numObjs; i++){
            int o = b.get(i);
            if (!d.contains(o)){
                d.add(o);
            }
        }
        
        return d;
    }
    
    public DoubleBag deduplicate(DoubleBag b){
        DoubleBag d = new DoubleBag();
        
        for (int i = 0; i < b.numObjs; i++){
            double o = b.get(i);
            if (!d.contains(o)){
                d.add(o);
            }
        }
        
        return d;
    }
    
    public int position(Bag b, int num){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (o.getClass() == int.class){
                int bNum = (int)o;
                if (bNum == num){
                    pos = i;
                    break;
                }
            }
        }         
        return pos;
    }
    
    public int position(IntBag b, int num){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            int bNum = b.get(i);
            if (bNum == num){
                pos = i;
                break;
            } 
        }
        return pos;
    }
    
    public int position(DoubleBag b, double num){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            double bNum = b.get(i);
            if (bNum == num){
                pos = i;
                break;
            } 
        }
        return pos;
    }
    
    public int position(Bag b, double num){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (o.getClass() == double.class){
                double bNum = (double)o;
                if (bNum == num){
                    pos = i;
                    break;
                }
            }
        }       
        return pos;
    }
    
    public int position(Bag b, String s){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (o.getClass() == String.class){
                String str = (String)o;
                if (str.equals(s)){
                    pos = i;
                    break;
                }
            }
        }
        return pos;
    }
    
    public int position(Bag b, Object obj){
        int pos = -1;
        for (int i = 0; i < b.numObjs; i++){
            Object o = b.get(i);
            if (o.getClass() == obj.getClass()){
                if (o.equals(obj)){
                    pos = i;
                    break;
                }
            }
        } 
        return pos;
    }
    
    
    
    public IntBag sortOrder(double [] b){
        DoubleBag db = new DoubleBag();
        db.addAll(b);
        return sortOrder(db);
    }
    
    public IntBag sortOrder(DoubleBag b){
        IntBag sortOrd = new IntBag();
        
        // THIS SHOULD BE ACCEPTABLE IN SPEED FOR SHORT LISTS (TO 150)

        IntBag order = new IntBag(b.numObjs);
        for (int i = 0; i < b.numObjs; i++){
            order.add(0);
        }

        if (b.numObjs == 1){
            return order;
        } else {

            DoubleBag sortedB = new DoubleBag();
            DoubleBag unsortedB = new DoubleBag();
            unsortedB.addAll(b);
            sortedB.addAll(b);
            sortedB.sort();
            
            IntBag unmatchedElements = new IntBag();
            for (int i = 0; i < b.numObjs; i++){
                unmatchedElements.add(i);      // initially, all elements are unmatched. Remove them once matched.
                                                  // This handles duplicates and it also cuts down iteration time, below
            }

            // iterate through the unmatched elements in the unsorted bag
            for (int i = 0; i < b.numObjs; i++){
                // look for match in the sorted bag and record the index number
                for (int k = 0; k < unmatchedElements.numObjs; k++ ){
                    int j = unmatchedElements.get(k);
                    if (sortedB.get(j)>=0 &&
                        unsortedB.get(i) == sortedB.get(j)) {
                        order.set(i, j);
                        // since this element is matched, remove it from the unmatched list
                        unmatchedElements.removeNondestructively(k);
                        
                    }
                }

            }

            if (order.numObjs < b.numObjs){
                System.err.println("ERROR in sorting");
                System.exit(0);
            }

            return order;
        }
    }
        
        
    


}
