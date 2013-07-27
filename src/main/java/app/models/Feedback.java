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

    public static class Builder {

        public Builder() {
            this.item = new Feedback();
        }
        private Feedback item;

        public Builder withId(final Long id) {
            this.item.setId(id);
            return this;
        }

        public Builder withMessage(final String message) {
            this.item.message = message;
            return this;
        }

        public Builder withPlayer(final Player player) {
            this.item.player = player;
            return this;
        }

        public Feedback build() {
            return this.item;
        }
    }
}
