/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import sim.util.Bag;

/**
 *
 * @author Russell Thomas
 */
public class ObjectUtilities {
    
     public static final String getClassNames(Bag b){
         String outString = "";
         String comma = "";
         for (Object o : b){
             outString =  outString + comma + o.getClass().getSimpleName();
             comma = ", ";
         }
         return outString;
     }

}
