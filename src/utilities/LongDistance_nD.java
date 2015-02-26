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
public interface LongDistance_nD {   // mostly for Manhattan Distance
    public long getDistance(Datum v1, Datum v2);

    public long getDistance(double [] v1, double [] v2);
    
    public long getDistance(Double_nD pt1, Double_nD pt2);
    
    public long getDistance(long [] v1, long [] v2);
    
    public long getDistance(int [] v1, int [] v2);
}
