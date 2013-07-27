package app.models;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class PlayerCredentials extends Entity {

	private String username;
	private String password;

        @OneToOne(cascade = CascadeType.ALL, optional = false, mappedBy = "credentials")
        private Player player;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
        
        
}
