/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.session;

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

    public SessionManager() {
        facebook = new FacebookFactory().getInstance();
    }

    public Facebook getFacebook() {
        return facebook;
    }
    
}
