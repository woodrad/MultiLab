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
import sim.models.simpleConsumption.Constants;

public class LogicalOperator {
    // constants
    final static int LE = 0;
    final static int LT = 1;
    final static int EQ = 2;
    final static int NE = 3;
    final static int GT = 4;
    final static int GE = 5;
                    
    
    String op = "";
    Object lhs = new Object();
    Object rhs = new Object();
    int methodIndex = -1;
    
    public LogicalOperator(String s){
        if (s.equals(Constants.LE)){
            this.methodIndex = LE;
        } else if (s.equals(Constants.LT)){
            this.methodIndex = LT;
        } else if (s.equals(Constants.EQ)){
            this.methodIndex = EQ;
        } else if (s.equals(Constants.NE)){
            this.methodIndex = NE;
        } else if (s.equals(Constants.GT)){
            this.methodIndex = GT;
        } else if (s.equals(Constants.GE)){
            this.methodIndex = GE;
        }
    }
    
    public LogicalOperator(String s, Object l, Object r) {
        this.lhs = l;
        this.rhs = r;
        if (s.equals(Constants.LE)) {
            this.methodIndex = LE;
        } else if (s.equals(Constants.LT)) {
            this.methodIndex = LT;
        } else if (s.equals(Constants.EQ)) {
            this.methodIndex = EQ;
        } else if (s.equals(Constants.NE)) {
            this.methodIndex = NE;
        } else if (s.equals(Constants.GT)) {
            this.methodIndex = GT;
        } else if (s.equals(Constants.GE)) {
            this.methodIndex = GE;
        }
    }
    public void setOperants(Object l, Object r){
        lhs = l;
        rhs = r;
    }
    
    public BooleanOrNAorError evaluate(){
        BooleanOrNAorError result = new BooleanOrNAorError();
        switch (methodIndex){
                case LE: 
                    result = lessThanOrEqual(lhs,rhs);
                    break;
                case LT:
                    result = lessThan(lhs,rhs);
                    break;
                case EQ:
                    result = equivalent(lhs,rhs);
                    break;
                case NE:
                    result = notEquivalent(lhs,rhs);
                    break;
                case GT:
                    result = greaterThan(lhs,rhs);
                    break;
                case GE:
                    result = greaterThanOrEqual(lhs,rhs);
                    break;
                default: ;
        }
        
        return result;
    }
    
    
    private TypeConversion testAndConvertTypes(Object a, Object b) {
        TypeConversion t = new TypeConversion();
        if (a.getClass() == String.class && b.getClass() == String.class) {
            t.type = Constants.STRING;
            t.resultA = a;
            t.resultB = b;
        } else  if ((a.getClass() == int.class 
                || a.getClass() == float.class 
                || a.getClass() == double.class 
                || a.getClass() == Integer.class 
                || a.getClass() == Float.class 
                || a.getClass() == Double.class)  
                && 
                (b.getClass() == int.class 
                || b.getClass() == float.class 
                || b.getClass() == double.class 
                || b.getClass() == Integer.class 
                || b.getClass() == Float.class 
                || b.getClass() == Double.class)
                ) {
                t.type = Constants.NUMERIC;
                t.resultA = (Double)a;
                t.resultB = (Double)b;
        } else {
            t.type = Constants.OTHER;
            t.resultA = a;
            t.resultB = b;
        }
       
        return t;
    }
    
    private BooleanOrNAorError lessThanOrEqual(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) <= 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) <= 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();
                break;
        }
        return result;
    }
    
    private BooleanOrNAorError lessThan(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) < 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) < 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();
                break;
        }
        return result;
    }
    
    private BooleanOrNAorError equivalent(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) == 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) == 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();  // change this to test object equivalence
                break;
        }
        return result;
    }
    
    private BooleanOrNAorError notEquivalent(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) != 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) != 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();  // change this to test object equivalence
                break;
        }
        return result;
    }
        
    private BooleanOrNAorError greaterThan(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) > 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) > 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();  // change this to test object equivalence
                break;
        }
        return result;
    }        

    private BooleanOrNAorError greaterThanOrEqual(Object l, Object r) {
        BooleanOrNAorError result = new BooleanOrNAorError();
        TypeConversion t = testAndConvertTypes(l,r);
        switch (t.type){
            case Constants.STRING: 
                    String lString = (String) t.resultA;
                    String rString = (String) t.resultB;
                    if (lString.compareTo(rString) >= 0) { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;
            case Constants.NUMERIC:
                    Double lVar = (Double) t.resultA;
                    Double rVar = (Double) t.resultB;
                    if (lVar.compareTo(rVar) >= 0)  { 
                        result.setTrue();
                    } else {
                        result.setFalse();
                    }
                    break;                
            default: 
                result.setError();  // change this to test object equivalence
                break;
        }
        return result;
    }
    
    class TypeConversion {
        int type =  Constants.OTHER;
        Object resultA = new Object();
        Object resultB = new Object();
    }
   /*  
    public final static String LT = "<";
    public final static String GT = ">";
    public final static String EQ = "==";
    public final static String NE = "!=";  
    public final static String AND = "&&";
    public final static String OR = "||";
    public final static String NOT = "!";
    */

}
