/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.utilities;

import java.util.InputMismatchException;

/**
 *
 * @author Russell Thomas
 */
public class ManhattanDistance implements LongDistance_nD {

@Override
    public long getDistance(Datum d1, Datum d2){
        long distance = 0;
        double [] pt1 = d1.getVector();
        double [] pt2 = d2.getVector();
        if (pt1.length != pt2.length) {
            throw new InputMismatchException();
        }

        for (int i=0; i<pt1.length; i++){
            distance+= Math.abs((long)pt1[i]-(long)pt2[i]);
        }
        return distance;
    }

    @Override
    public long getDistance(double [] pt1, double [] pt2){
        long distance = 0;
        
        if (pt1.length != pt2.length) {
            System.err.println("Point 1 length = " + pt1.length + " != " + pt2.length );
            throw new InputMismatchException();
        }
        for (int i=0; i<pt1.length; i++){
            distance+= Math.abs((long)pt1[i]-(long)pt2[i]);
        }
        return distance;
    }
    
@Override
    public long getDistance(Double_nD pt1, Double_nD pt2){
        return getDistance(pt1.dim, pt2.dim);
    }
    
@Override
    public long getDistance(int [] pt1, int [] pt2){
        long distance = 0;
        
        if (pt1.length != pt2.length) {
            System.err.println("Point 1 length = " + pt1.length + " != " + pt2.length );
            throw new InputMismatchException();
        }
        for (int i=0; i<pt1.length; i++){
            distance+= Math.abs((long)pt1[i]-(long)pt2[i]);
        }
        return distance;
        
    }
    
    @Override
    public long getDistance(long [] pt1, long [] pt2){
        long distance = 0;
        
        if (pt1.length != pt2.length) {
            System.err.println("Point 1 length = " + pt1.length + " != " + pt2.length );
            throw new InputMismatchException();
        }
        for (int i=0; i<pt1.length; i++){
            distance+= Math.abs(pt1[i]-pt2[i]);
        }
        return distance;
        
    }
}
