/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package characteristics;


import sim.util.*;
/**
 *
 * @author Russell Thomas
 */
public class GridLocation extends Characteristic {
    Int2D value;  // hides "value" in class Characteristic
    
    public void setValue(Int2D loc){
        value = new Int2D(loc.x,loc.y);
    }
}
