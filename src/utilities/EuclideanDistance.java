/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

/**
 *
 * @author Russell Thomas
 */
import java.util.InputMismatchException;
import utilities.Datum;


public class EuclideanDistance implements Distance_nD {
    @Override
    public double getDistance(Datum d1, Datum d2){
        double distance = 0.0d;
        double [] pt1 = d1.getVector();
        double [] pt2 = d2.getVector();
        if (pt1.length != pt2.length) {
            throw new InputMismatchException();
        }
        double sumOfSquares=0;
        int size = pt1.length;
        for (int i=0; i<size; i++){
            sumOfSquares+=Math.pow((pt1[i]-pt2[i]),2);
        }
        distance = Math.sqrt(sumOfSquares);
        return distance;
    }

    @Override
    public double getDistance(double [] pt1, double [] pt2){
        double distance = 0.0;
        
        if (pt1.length != pt2.length) {
            System.err.println("Point 1 length = " + pt1.length + " != " + pt2.length );
            throw new InputMismatchException();
        }
        
        
        double sumOfSquares=0.0;
        int size = Math.min(pt1.length,pt2.length) ;
        for (int i=0; i<size; i++){
            sumOfSquares+=Math.pow((pt1[i]-pt2[i]),2);
        }
        distance = Math.sqrt(sumOfSquares);
        return distance;
    }
    
    @Override
    public double getDistance(Double_nD pt1, Double_nD pt2){
        return getDistance(pt1.dim, pt2.dim);
    }
    
    @Override
    public double getDistance(int [] pt1, int [] pt2){
        double [] pt1d = new double[pt1.length];
        for (int i = 0; i < pt1.length; i++){
            pt1d[i] = (double)pt1[i];
        }
        double [] pt2d = new double[pt2.length];
        for (int i = 0; i < pt1.length; i++){
            pt2d[i] = (double)pt2[i];
        }
        return(getDistance(pt1d,pt2d));
        
    }
    
        @Override
    public double getDistance(long [] pt1, long [] pt2){
        double [] pt1d = new double[pt1.length];
        for (int i = 0; i < pt1.length; i++){
            pt1d[i] = (double)pt1[i];
        }
        double [] pt2d = new double[pt2.length];
        for (int i = 0; i < pt1.length; i++){
            pt2d[i] = (double)pt2[i];
        }
        return(getDistance(pt1d,pt2d));
        
    }

}

