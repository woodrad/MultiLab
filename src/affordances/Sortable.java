/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package affordances;



/**
 *
 * @author Russell Thomas
 */
import capabilities.*;

public class Sortable extends Affordance {
    private static final long serialVersionUID = 12;
    
    public Sortable(){
        Sorting s = new Sorting();
        this.relevantCapabilities.add(s);
    }
    
}
