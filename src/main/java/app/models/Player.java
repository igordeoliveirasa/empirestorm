package app.models;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Player extends Entity {
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private PlayerCredentials credentials;

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

        public Builder withName(final String name) {
            this.item.name = name;
            return this;
        }

        public Builder withCredentials(final PlayerCredentials credentials) {
            this.item.credentials = credentials;
            return this;
        }

        public Player build() {
            return this.item;
        }
    }
}
