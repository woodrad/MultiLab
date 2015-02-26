/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package affordances;

import capabilities.Scanning;

/**
 *
 * @author Russell Thomas
 */
public class Scannable extends Affordance{
        private static final long serialVersionUID = 13;
    
    public Scannable(){
        Scanning s = new Scanning();
        this.relevantCapabilities.add(s);
    }

}
