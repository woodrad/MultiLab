/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package space;

import sim.field.grid.SparseGrid2D;
import sim.util.Int2D;
import utilities.Datum;
import utilities.*;


/**
 *
 * @author Russell Thomas
 */
public abstract class MetricSpace extends Space implements EngageMetricSpace{
    final EuclideanDistance euclideanDistance = new EuclideanDistance();
    final ManhattanDistance manhattanDistance = new ManhattanDistance();
    Double_nD focus;
    
    public void setFocus(Double_nD f){
        focus = new Double_nD(f);
    }
    
    public Double_nD getFocus(){
        return focus;
    }
    
    public double euclideanDistance(Datum v1, Datum v2){
        return euclideanDistance.getDistance(v1, v2);
        
    }
    
    public double euclideanDistance(Double_nD v1, Double_nD v2){
        return euclideanDistance.getDistance(v1, v2);
    }

    public double euclideanDistance(double [] v1, double [] v2){
        return euclideanDistance.getDistance(v1, v2);
    }
    
    public double euclideanDistanceSq(Datum v1, Datum v2){
        double d = euclideanDistance.getDistance(v1, v2);
        return (d*d);
        
    }
    
    public double euclideanDistanceSq(Double_nD v1, Double_nD v2){
        double d = euclideanDistance.getDistance(v1, v2);
        return (d*d);
    }

    public double euclideanDistanceSq(double [] v1, double [] v2){
        double d = euclideanDistance.getDistance(v1, v2);
        return (d*d);
    }
    
    public double manhattanDistance(Datum v1, Datum v2){
        return manhattanDistance.getDistance(v1, v2);
        
    }
    
    public double manhattanDistance(Double_nD v1, Double_nD v2){
        return manhattanDistance.getDistance(v1, v2);
    }

    public double manhattanDistance(double [] v1, double [] v2){
        return manhattanDistance.getDistance(v1, v2);
    }
}
