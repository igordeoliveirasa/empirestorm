/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.session;

import app.controllers.*;
import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author igor
 */
public class SessionManagerTest {
        
    @Mock
    private Facebook facebook;
    private SessionManager sessionManager;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sessionManager = new SessionManager();
    }
    
    @Test
    public void getFacebook() {
        assertNotNull(sessionManager.getFacebook());
    }
    
    @Test
    public void signInAndSignOut() {
        sessionManager.signIn(new Player.Builder().withId(1L).build());
        assertTrue(sessionManager.isSignedIn());
        assertEquals(sessionManager.getPlayer().getId(), 1L, 0);
        
        sessionManager.signOut();
        assertFalse(sessionManager.isSignedIn());
    }
        
    
}