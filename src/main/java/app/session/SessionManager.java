/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.session;

import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlayerRepository;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;

/**
 *
 * @author igor
 */

@Component
@SessionScoped
public class SessionManager {
    private Facebook facebook;
    private Player player = null;
    
    public SessionManager() {
        this.facebook = new FacebookFactory().getInstance();
    }

    public Facebook getFacebook() {
        return facebook;
    }    

    public void signIn(Player player) {
        this.player = player;
    }
    
    public void signOut() {
        this.player = null;
    }
    
    public boolean isSignedIn() {
        return this.player != null;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Player getPlayer(PlayerRepository playerRepository) {
        this.player = playerRepository.find(player.getId());
        return getPlayer();
    }
}
