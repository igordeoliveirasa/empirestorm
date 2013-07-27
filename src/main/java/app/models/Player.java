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
}
