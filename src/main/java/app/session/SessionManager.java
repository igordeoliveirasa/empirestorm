/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.session;

import app.models.Player;
import app.models.PlayerCredentials;
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
    private Player player;
    
    public SessionManager() {
        facebook = new FacebookFactory().getInstance();
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
}
