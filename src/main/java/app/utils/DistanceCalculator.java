/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import java.awt.Point;

/**
 *
 * @author igor
 */
@Component
@ApplicationScoped
public class DistanceCalculator {
    public double calculateDistance(int x1, int y1, int x2, int y2) {
        Point pointA = new Point(x1, y1);
        Point pointB = new Point(x2, y2);
        return pointA.distance(pointB);
    }
}
