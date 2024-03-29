package app.models;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Player extends Entity {
    private String name;
    private int gold;
    private double life;
    
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private PlayerCredentials credentials;
    
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Place place;
    
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private PlayerSkills skills = new PlayerSkills.Builder().withWalkVelocity(1).build();
    
    public void setName(String name) {
            this.name = name;
    }

    public String getName() {
            return name;
    }

    public PlayerCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(PlayerCredentials credentials) {
        this.credentials = credentials;
    }    

    public static class Builder {

        public Builder() {
            this.item = new Player();
        }
        private Player item;

        public Builder withId(final Long id) {
            this.item.setId(id);
            return this;
        }

        public Builder withLife(final double life) {
            this.item.life = life;
            return this;
        }

        public Builder withSkills(final PlayerSkills skills) {
            this.item.setSkills(skills);
            return this;
        }

        public Builder withName(final String name) {
            this.item.name = name;
            return this;
        }

        public Builder withPlace(final Place place) {
            this.item.place = place;
            return this;
        }

        public Builder withCredentials(final PlayerCredentials credentials) {
            this.item.credentials = credentials;
            return this;
        }
        
        public Builder withGold(final int gold) {
            this.item.gold = gold;
            return this;
        }
        
        

        public Player build() {
            return this.item;
        }
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public PlayerSkills getSkills() {
        return skills;
    }

    public void setSkills(PlayerSkills skills) {
        this.skills = skills;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }
}
