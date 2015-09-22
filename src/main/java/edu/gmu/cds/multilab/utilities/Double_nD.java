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

import java.util.InputMismatchException;
import sim.util.*;

public final class Double_nD implements java.io.Serializable {  
    // Generalizes Double2D and Double3D to n dimensions

    private static final long serialVersionUID = 1;
    
    
    public final double [] dim;
    public final int n; // must be >=1
    final EuclideanDistance euclideanDistance = new EuclideanDistance();
    final ManhattanDistance manhattanDistance = new ManhattanDistance();
    
    public Double_nD (){
        // Default to two dimensions
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = 0.0;
        dim[1] = 0.0;
    }
    
    // accept 2D and 3D Integers and Doubles
    // Compatible with Int2D, Int3D, Double2D, and Double3D constructors
    public Double_nD(final java.awt.Point p) {
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = p.x;
        dim[1] = p.y;
    }
    
    public Double_nD(final MutableInt2D p) {
        this.n = 2;
        this.dim = new double [this.n];

    }

    public Double_nD(final int x, final int y){ 
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = (double)x;
        dim[1] = (double)y;
    }
    
    public Double_nD(Int2D pt){ 
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = (double)pt.x;
        dim[1] = (double)pt.y;
    }
    
    public Double_nD(final int x, final int y, final int z){ 
        this.n = 3;
        this.dim = new double [this.n];
        dim[0] = (double)x;
        dim[1] = (double)y;
        dim[2] = (double)z;
    }
    
    public Double_nD(Int3D pt){ 
        this.n = 3;
        this.dim = new double [this.n];
        dim[0] = (double)pt.x;
        dim[1] = (double)pt.y;
        dim[2] = (double)pt.z;
    }
    
    public Double_nD(final int[] x) {
        if (x.length >= 1) {
            this.n = x.length;
            this.dim = new double[this.n];
            for (int i = 0; i < n; i++) {
                dim[i] = (double) x[i];
            }
        } else {
            System.err.println("n Dimensional point has less than 1 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    public Double_nD(final double x, final double y){ 
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = x;
        dim[1] = y;
    }
    
    public Double_nD(Double2D pt){ 
        this.n = 2;
        this.dim = new double [this.n];
        dim[0] = pt.x;
        dim[1] = pt.y;
    }
    
    public Double_nD(final double x, final double y, final double z){ 
        this.n = 3;
        this.dim = new double [this.n];
        dim[0] = x;
        dim[1] = y;
        dim[2] = z;
    }
    
    public Double_nD(Double3D pt){ 
        this.n = 3;
        this.dim = new double [this.n];
        dim[0] = pt.x;
        dim[1] = pt.y;
        dim[2] = pt.z;
    }
    
    public Double_nD(final double [] x){ 
        this.n = x.length;
        this.dim = new double [this.n];
        System.arraycopy(x, 0, dim, 0, n);
    }
    
    public Double_nD(Double_nD p){ 
        this.n = p.n;
        this.dim = new double [this.n];
        System.arraycopy(p.dim, 0, dim, 0, n);
    }
    
    public Double_nD(final Bag x) {
        if (x.numObjs >= 1) {
            this.n = x.numObjs;
            this.dim = new double[this.n];
            for (int i = 0; i < n; i++) {
                double val = (double) x.get(i);
                dim[i] = val;
            }
        } else {
            System.err.println("n Dimensional point has less than 1 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }

    public Double_nD(final IntBag x) {
        if (x.numObjs >= 1) {
            this.n = x.numObjs;
            this.dim = new double[this.n];
            for (int i = 0; i < n; i++) {
                double val = (double) x.get(i);
                dim[i] = val;
            }
        } else {
            System.err.println("n Dimensional point has less than 1 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    public Double_nD(final DoubleBag x) {
        if (x.numObjs >= 1) {
            this.n = x.numObjs;
            this.dim = new double[this.n];
            for (int i = 0; i < n; i++) {
                dim[i] = x.get(i);
            }
        } else {
            System.err.println("n Dimensional point has less than 1 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    
    public final double getX() { 
        if (n>= 1){
            return dim[0]; 
        } else {
            return 0;   // this should throw an error instead of returning a value
        }
    }
    public final double getY() { 
        if (n>= 2){
            return dim[1]; 
        } else {
            System.err.println("n Dimensional point has less than 2 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    public final double getZ() { 
        if (n>= 3){
            return dim[2]; 
        } else {
            System.err.println("n Dimensional point has less than 3 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    public java.awt.geom.Point2D.Double toPoint2D() { // assumes first two dimensions are X and Y
        if (n>=2){
            return new java.awt.geom.Point2D.Double(dim[0],dim[1]); 
        } else {
            System.err.println("n Dimensional point has less than 2 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    
    public java.awt.geom.Point2D.Double toPoint2D(int dimX, int dimY) {
        if (n>=2 && dimX >= 0 & dimX < n - 1 && dimY >= 0 & dimY < n - 1){
            return new java.awt.geom.Point2D.Double(dim[dimX],dim[dimY]); 
        } else {
            System.err.println("n Dimensional point has less than 2 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions
        }
    }
    

    public java.awt.Point toPoint() {  // assumes first two dimensions are X and Y
        if (n>=2){
            return new java.awt.Point((int)dim[0],(int)dim[1]); 
        } else {
            System.err.println("n Dimensional point has less than 2 dimensions.");
            throw new InputMismatchException();  //  not enough dimensions       
        }
    }
    
    public java.awt.Point toPoint(int dimX, int dimY) {  // set  X and Y dimensions
        if (n>=2 && dimX >= 0 & dimX < n - 1 && dimY >= 0 & dimY < n - 1){
            return new java.awt.Point((int)dim[dimX],(int)dim[dimY]); 
        } else {
            System.err.println("n Dimensional point has less than 2 dimensions, or index of dimensions is out of bounds");
            throw new InputMismatchException();  //  not enough dimensions

        }
    }
    @Override
    public String toString() {
        String result = "Double_n_D[";
        String comma = "";
        for (int i = 0; i<n; i++){
            result = result+ comma + dim[i];
            comma = ", ";
        }
        result = result + "]";
        return result;
    }
    
    public String toCoordinates() {
        String result = "(";
        String comma = "";
        for (int i = 0; i<n; i++){
            result = result+ comma + dim[i];
            comma = ", ";
        }
        result = result + ")";
        return result;
    
}

/*
*/

    // this is required because we have overridden the equals() method
    @Override
    public int hashCode() {
    // code adapated from Double3D, generalized to n dimensions
        // Most comments and commentary are by Sean Luke
        double[] hashableVals = this.dim;

        // push -0.0 to 0.0 for purposes of hashing.  Note that equals() has also been modified
        // to consider -0.0 to be equal to 0.0.  Hopefully cute Java compilers won't try to optimize this out.
        for (double x : hashableVals) {
            if (x == -0.0) {
                x = 0.0;
            }
        }

        boolean intTest = false;
        for (double x : hashableVals) {
            intTest = intTest && ((int) x) == x;
        }
        // so we hash to the same value as Int2D does, if we're ints
        if (intTest) {
            //  return Int3D.hashCodeFor((int)x,(int)y,(int)z);, generalized to n dimensions

            int[] hashableIntVal = new int[hashableVals.length];
            for (int i = 0; i < hashableVals.length; i++) {
                hashableIntVal[i] = (int) hashableVals[i];
            }

            // copied from Int3D (and modified by RCT) and inserted here because hashCodeFor can't be
            // inlined and this saves us a fair chunk on some hash-heavy applications
            // RCT: but because I'm doing it in a loop, there is probably no performace advantages
            int z_ = 0;
            for (int i = hashableIntVal.length - 1; i > 0; i--) {
                z_ ^= hashableIntVal[i];
                z_ += 17;    // a little prime number shifting -- waving a dead chicken?  dunno
                z_ += ~(z_ << 15);
                z_ ^= (z_ >>> 10);
                z_ += (z_ << 3);
                z_ ^= (z_ >>> 6);
                z_ += ~(z_ << 11);
                z_ ^= (z_ >>> 16);
            }

            // nifty!  Now mix in x
            int x_ = hashableIntVal[0];
            return x_ ^ z_;
        }

        // I don't like Sun's simplistic approach to random shuffling.  So...
        // basically we need to randomly disperse <double,double,double> --> int
        // We do this by doing <double,double,double> -> <long,long,long> -> long -> int
        // The first step is done with doubleToLongBits (not RawLongBits;
        // we want all NaN to hash to the same thing).  Then conversion to
        // a single long is done by hashing (shuffling) z, then xoring it with y,
        // then hashing that and xoring with x.
        // I do that as x ^ hash(y ^ hash(z) + 17 [or whatever]). Hash function
        // taken from http://www.cris.com/~Ttwang/tech/inthash.htm
        // Some further discussion.  Sun's moved to a new hash table scheme
        // which has (of all things!) tables with lengths that are powers of two!
        // Normally hash table lengths should be prime numbers, in order to
        // compensate for bad hashcodes.  To fix matters, Sun now is
        // pre-shuffling the hashcodes with the following algorithm (which
        // is short but not too bad -- should we adopt it?  Dunno).  See
        // http://developer.java.sun.com/developer/bugParade/bugs/4669519.html
        //    key += ~(key << 9);
        //    key ^=  (key >>> 14);
        //    key +=  (key << 4);
        //    key ^=  (key >>> 10);
        // This is good for us because Int2D, Int3D, Double2D, and Double3D
        // have hashcodes well distributed with regard to y and z, but when
        // you mix in x, they're just linear in x.  We could do a final
        // shuffle I guess.  In Java 1.3, they DON'T do a pre-shuffle, so
        // it may be suboptimal.  Since we're all moving to 1.4.x, it's not
        // a big deal since 1.4.x is shuffling the final result using the
        // Sun shuffler above.  But I'd appreciate some tests on our method
        // below, and suggestions as to whether or not we should adopt the
        // shorter, likely suboptimal but faster Sun shuffler instead
        // for y and z values.  -- Sean
        long key = 0;
        for (int i = hashableVals.length - 1; i > 0; i--) {
            key ^= Double.doubleToLongBits(hashableVals[i]);
            key += 17;    // a little prime number shifting -- waving a dead chicken?  dunno
            key += ~(key << 32);
            key ^= (key >>> 22);
            key += ~(key << 13);
            key ^= (key >>> 8);
            key += (key << 3);
            key ^= (key >>> 15);
            key += ~(key << 27);
            key ^= (key >>> 31);
        }

            // nifty!  Now mix in x
        key ^= Double.doubleToLongBits(hashableVals[0]);
        return (int)key;
    }
        
    
    // can't have separate equals(...) methods as the
    // argument isn't virtual
    public final boolean equals(Object obj)  // only equals == true if identical number of dimensions
        {
        if (obj==null) return false;
        else if (obj instanceof Double_nD )  // n dimensions first
            {
            Double_nD other = (Double_nD) obj;
            boolean result = true;
            if (other.n == this.n){
                for (int i = 0; i < n; i++){
                result = result && other.dim[i] == dim[i];
                }
                return result;
            } else {
                return false; // number of dimensions don't match
            }
            }
        else if (obj instanceof Int2D && n ==2)  // 2D
            {
            Int2D other = (Int2D) obj;
            return (other.x == dim[0] && other.y == dim[1]);
            }
        else if (obj instanceof MutableInt2D && n ==2)
            {
            MutableInt2D other = (MutableInt2D) obj;
            return (other.x == dim[0] && other.y == dim[1]);
            }
        else if (obj instanceof Double2D && n ==2)
            {
            Double2D other = (Double2D) obj;
            return (other.x == dim[0] && other.y == dim[1]);
            }
        else if (obj instanceof MutableDouble2D && n ==2)
            {
            MutableDouble2D other = (MutableDouble2D) obj;
            return (other.x == dim[0] && other.y == dim[1]);
            }
        else if (obj instanceof Int3D && n ==3)  // now 3D 
            {
            Int2D other = (Int2D) obj;
            return (other.x == dim[0] && other.y == dim[1]);
            }
        else if (obj instanceof MutableInt3D && n ==3)
            {
            MutableInt3D other = (MutableInt3D) obj;
            return (other.x == dim[0] && other.y == dim[1]  && other.z == dim[2]);
            }
        else if (obj instanceof Double3D && n ==3)
            {
            Double3D other = (Double3D) obj;
            return (other.x == dim[0] && other.y == dim[1]  && other.z == dim[2]);
            }
        else if (obj instanceof MutableDouble3D && n ==3)
            {
            MutableDouble3D other = (MutableDouble3D) obj;
            return (other.x == dim[0] && other.y == dim[1]  && other.z == dim[2]);
            }
        else return false;
        }


    /** Returns the distance FROM this n Dim point TO the specified point */
    public double distance(final double [] x)
        {
        return euclideanDistance.getDistance(x, dim);
        }

    /** Returns the distance FROM this n Dim TO the specified point.   */
    public double distance(final Double_nD p)
        {
        return euclideanDistance.getDistance(p, this);
        }
    
    /** Returns the distance squared FROM this n Dim point TO the specified point */
    public double distanceSq(final double[] x) {
        double d = 0;
        d = euclideanDistance.getDistance(x, dim);
        return (d * d);
    }

    /** Returns the distance squared FROM this n Dim TO the specified point.   */
    public double distanceSq(final Double_nD p){
        double d = 0;
        d =  euclideanDistance.getDistance(p, this);
        return d;
    }

    

    /** Returns the manhattan distance FROM this n Dim TO the specified point.    */
    public long manhattanDistance(final double [] x)
        {
        return manhattanDistance.getDistance(x,dim);
        }

    /** Returns the manhattan distance FROM this n Dim TO the specified point.    */
    public long manhattanDistance(Double_nD p)
        {
        return manhattanDistance.getDistance(p,this);
        }
        
}
