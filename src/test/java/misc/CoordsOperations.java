/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor
 */
public class CoordsOperations {
    
    public CoordsOperations() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void calculateDistanceBetween() {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(20, 15);
               
        
        double distance = Math.sqrt(Math.pow(pointB.getX()-pointA.getX(),2.0)+Math.pow(pointB.getY()-pointA.getY(), 2.0));
        assertEquals(distance, 25, 0);
        
        assertEquals(pointA.distance(pointB), 25, 0);
   }
}