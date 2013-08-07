package app.models;

import java.awt.Point;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import org.joda.time.Minutes;

@javax.persistence.Entity
public class PlayerActionWalk extends Entity {
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Player player;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Place fromPlace;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
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
        DecimalFormat f = new DecimalFormat("#.##");    
        return Double.parseDouble(f.format(pointA.distance(pointB)));
    }
    
    public String getFormattedDistance() {
        DecimalFormat f = new DecimalFormat("#,###,##0.00");    
        return f.format(getDistance()) + " kms";
    }    
    
    public double getDurationInMinutes() {
        return (this.getDistance() * player.getSkills().getWalkVelocity());
    }
    
    public String getFormattedDuration() {        
        double totalMinutes = getDurationInMinutes();
        return totalMinutes + " mins";
    }    
    
    public double getProgressValue() { 
        long firstTime = getCreatedAt().getTime();
        long currentTime = new Date().getTime();
        double endTime = (firstTime + getDurationInMinutes() * 60 * 1000);
        
        double diffTime1 = currentTime - firstTime;
        double diffTime2 = endTime - firstTime;
        
        return diffTime1 / diffTime2;   
    }
    
    public long getCurrentTime() {
        return new Date().getTime();
    }

    public static class Builder {

        public Builder() {
            this.item = new PlayerActionWalk();
        }
        private PlayerActionWalk item;

        public Builder withId(final Long id) {
            this.item.setId(id);
            return this;
        }

        public Builder withCreatedAt(final Date date) {
            this.item.setCreatedAt(date);
            return this;
        }

        public Builder withPlayer(final Player player) {
            this.item.player = player;
            return this;
        }

        public Builder withFromPlace(final Place fromPlace) {
            this.item.fromPlace = fromPlace;
            return this;
        }

        public Builder withToPlace(final Place toPlace) {
            this.item.toPlace = toPlace;
            return this;
        }

        public Builder withFinalized(final boolean finalized) {
            this.item.finalized = finalized;
            return this;
        }

        public PlayerActionWalk build() {
            return this.item;
        }
    }
}
