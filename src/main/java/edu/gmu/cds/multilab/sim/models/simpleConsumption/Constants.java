/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gmu.cds.multilab.sim.models.simpleConsumption;

import java.awt.Color;

/**
 *
 * @author Russell Thomas
 */
public class Constants {
    public static final boolean PRINT_RESULTS_STATUS_AND_DESC = true;
    
    public static final int GRID_HEIGHT = 100;
    public static final int GRID_WIDTH = 100;
    
    public static final Color NEUTRAL_COLOR = Color.gray;

    // constants related to simple product construction
    public static String [] PRODUCT_NODES = {"A","B","C","D","E","F","G","H","I","J","K","L"};
    
    public static String [] CORNER_NODES = {"A","D","G","J"};
    
    // one array for each of the twelve lettered nodes.  Excludes all default lines (see list below).
    public static String [][] FEASABLE_END_NODES = {
            {"E","F","G","H","I"},              // starting from "A"
            {"E","F","G","H","I","J","K","L"},  // starting from "B"
            {"E","F","G","H","I","J","K","L"},  // starting from "C"
            {"H","I","J","K","L"},              // starting from "D"
            {"A","B","C","H","I","J","K","L"},  // starting from "E"
            {"A","B","C","H","I","J","K","L"},  // starting from "F"
            {"A","B","C","K","L"},              // starting from "G"
            {"A","B","C","D","E","F","K","L"},  // starting from "H"
            {"A","B","C","D","E","F","K","L"},  // starting from "I"
            {"B","C","D","E","F"},              // starting from "J"
            {"B","C","D","E","F","G","H","I"},  // starting from "K"
            {"B","C","D","E","F","G","H","I"}   // starting from "L"
        };
    
    // these lines are all in the default product, and therefore are redundant in any design
    public final static String [][] DEFAULT_LINES = {   // there are 24 default lines
            {"A","B"},{"B","C"},{"C","D"},{"A","C"},{"A","D"},{"B","D"},
            {"D","E"},{"E","F"},{"F","G"},{"D","F"},{"D","G"},{"E","G"},
            {"G","H"},{"H","I"},{"I","J"},{"G","I"},{"G","J"},{"H","J"},
            {"J","K"},{"K","L"},{"L","A"},{"J","L"},{"J","A"},{"K","A"}
        };
    
    //design methods
    public final static int RANDOM_DESIGN = 0;
    
    // constants related to products
    public final static int MAX_LINES = 4;  // Total combinations = 124,313; 42 lines taken 1,2,3, or 4 at a time
                                            //max is 42 = 66 - 24; 
                                            //there are 66 possible combinations = 12! / (2! (12 - 2)!)
                                            //   less 24 default lines
    public final static int NUM_NODES = 12;
    
    
    // status and logical constants

    
    public final static int NA = 0;
    public final static int FALSE = -1;
    public final static int TRUE = 1;
    
    public final static int NULL_STATUS = 0;
    public final static int COMPLETED = 1;
    public final static int COMPLETED_BY_STOP_CONTITION = 2;
    public final static int NOT_COMPLETED = 3;
    public final static int ERROR = 4;
    public final static int UNABLE_TO_PERFORM = 5;
    public final static int MISSING_TARGET_OF_TASK = 6;
    public final static int PARTIALLY_OR_IMPERFECTLY_COMPLETED = 7;
    
    
    public final static int STATUS_OFFSET = 0;  // to convert between status codes and string index
    
    public static String [] STATUS_STRINGS = {
        "NULL_STATUS",
        "COMPLETED",
        "STOPPED",
        "NOT_COMPLETED",
        "ERROR",
        "UNABLE_TO_PERFORM",
        "MISSING_TARGET_OF_TASK",
        "PARTIALLY_OR_IMPERFECTLY_COMPLETED"
      
    };
    
    public final static int ASCENDING = 100;
    public final static int DECENDING = -100;
    
    // logical operators
    public final static String LE = "<=";
    public final static String LT = "<";
    public final static String GT = ">";
    public final static String GE = ">=";
    public final static String EQ = "==";
    public final static String NE = "!=";  
    public final static String AND = "&&";
    public final static String OR = "||";
    public final static String NOT = "!";
    
    public final static int NUMERIC = 1;
    public final static int STRING = 2;
    public final static int OTHER = 3;

}
