package app.models;

import java.awt.Point;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class PlayerActionWalk extends Entity {
    
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Place fromPlace;
    
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Place toPlace;
    
    
    
    private boolean finalized = false;

    public void setFinalized(boolean finalized) {
            this.finalized = finalized;
    }

    public boolean isFinalized() {
            return finalized;
    }

    public Place getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(Place fromPlace) {
        this.fromPlace = fromPlace;
    }

    public Place getToPlace() {
        return toPlace;
    }

    public void setToPlace(Place toPlace) {
        this.toPlace = toPlace;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public double getDistance() {
        Point pointA = new Point(fromPlace.getX(), fromPlace.getY());
        Point pointB = new Point(toPlace.getX(), toPlace.getY());
        return pointA.distance(pointB);
    }
    
    public String getFormattedDistance() {
        DecimalFormat f = new DecimalFormat("#,###,##0.00");    
        return f.format(getDistance()) + " kms";
    }    
    
    public long getDurationInMinutes() {
        return (long) (this.getDistance() * player.getSkills().getWalkVelocity());
    }
    
    public String getFormattedDuration() {        
        int totalMinutes = new Double(getDurationInMinutes()).intValue();
        return totalMinutes + " mins";
    }    
}
