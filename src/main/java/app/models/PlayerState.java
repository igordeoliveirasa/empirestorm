package app.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class PlayerState extends Entity {

    private double thirstyLevel = 0.95;
    private double hungryLevel = 0.55;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private List<Player> players = new ArrayList<Player>();

    public void setThirstyLevel(double thirstyLevel) {
            this.thirstyLevel = thirstyLevel;
    }

    public double getThirstyLevel() {
            return thirstyLevel;
    }

    public void setHungryLevel(double hungryLevel) {
            this.hungryLevel = hungryLevel;
    }

    public double getHungryLevel() {
            return hungryLevel;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }    
}
