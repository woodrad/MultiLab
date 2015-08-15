/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.multilab.utilities;

/**
 *
 * @author Russell Thomas
 */
public interface Distance_nD {
    public double getDistance(Datum v1, Datum v2);

    public double getDistance(double [] v1, double [] v2);
    
    public double getDistance(int [] v1, int [] v2);
    
    public double getDistance(long [] v1, long [] v2);
    
    public double getDistance(Double_nD pt1, Double_nD pt2);
}
