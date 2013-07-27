package app.models;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Feedback extends Entity {

    private String message;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Player player;

    public void setMessage(String message) {
            this.message = message;
    }

    public String getMessage() {
            return message;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
