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
public interface ScoredObject {
    
    public double getScore();
    public Object getObject();
    public void setScore(double s);
    public void setObject(Object o);

}
