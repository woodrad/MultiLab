/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.utilities;

/**
 *
 * @author Russell Thomas
 */
public interface Datum {
    public int length();
    public double[] getVector();
    public void setVector(double [] v);
   
}
