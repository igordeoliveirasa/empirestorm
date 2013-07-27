/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.vos;

import app.models.Place;

/**
 *
 * @author igor
 */
public class PlaceToTravel {
    private Place place;
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
