package app.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class PlayerState extends Entity {

    private double thirstyLevel;
    private double hungryLevel;
    
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

    public static class Builder {

        public Builder() {
            this.item = new PlayerState();
        }
        private PlayerState item;

        public Builder withThirstyLevel(final double thirstyLevel) {
            this.item.thirstyLevel = thirstyLevel;
            return this;
        }

        public Builder withHungryLevel(final double hungryLevel) {
            this.item.hungryLevel = hungryLevel;
            return this;
        }

        public Builder withPlayers(final List<Player> players) {
            this.item.players = players;
            return this;
        }

        public PlayerState build() {
            return this.item;
        }
    }
}
