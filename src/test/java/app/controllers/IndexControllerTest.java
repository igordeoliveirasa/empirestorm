/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

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
public class IndexControllerTest {
    
    private Validator validator;
    private Result result;
    
    private Result spyResult;
    
    @Mock
    private SessionManager sessionManager;
    @Mock
    private Facebook facebook;
    @Mock
    private User user;
    
    @Mock
    private PlayerCredentialsRepository playerCredentialsRepository;
    
    @Mock
    private PlayerRepository playerRepository;
    
    private IndexController indexController;
            
    @Before
    public void setUp() {
        validator = new MockValidator();
        result = new MockResult();
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(result, validator, sessionManager, playerCredentialsRepository, playerRepository);
    }

    @Test
    public void login() {
        when(facebook.getOAuthAuthorizationURL(anyString())).thenReturn("http://www.example.com/loginCallback?code=codeXYZ");
        when(sessionManager.getFacebook()).thenReturn(facebook);
        indexController.login();
    }
    
    @Test
    public void loginCallbackAlreadyRegistered() {
        try {
            when(user.getEmail()).thenReturn("igordeoliveirasa@gmail.com");
            when(facebook.getMe()).thenReturn(user);
            when(sessionManager.getFacebook()).thenReturn(facebook);
            
            Player player = new Player.Builder().withName("Igor de Oliveira Sá").build();
            PlayerCredentials playerCredentials = new PlayerCredentials.Builder().withUsername("igordeoliveirasa@gmail.com").withPassword("123").build();
            player.setCredentials(playerCredentials);            
            playerCredentials.setPlayer(player);
            when(playerCredentialsRepository.findByUsername("igordeoliveirasa@gmail.com")).thenReturn(playerCredentials);
            
            indexController.loginCallback("codeXYZ");
            class PlayerMatcher extends ArgumentMatcher<Player> {

                @Override
                public boolean matches(Object argument) {
                    Player player = (Player)argument;
                    return  player.getCredentials().getUsername().equals("igordeoliveirasa@gmail.com")&&
                            player.getCredentials().getPassword().equals("123") && 
                            player.getCredentials().getId()==null &&
                            player.getName().equals("Igor de Oliveira Sá");
                }
                
            }
            
            verify(sessionManager, times(1)).signIn(argThat(new PlayerMatcher()));
            
        } catch (FacebookException ex) {
            fail();
        }
    }
    
    @Test
    public void loginCallbackNotRegistered() {
        try {
            when(user.getEmail()).thenReturn("igordeoliveirasa@gmail.com");
            when(user.getName()).thenReturn("Igor de Oliveira Sá");
            when(facebook.getMe()).thenReturn(user);
            when(sessionManager.getFacebook()).thenReturn(facebook);
            
            when(playerCredentialsRepository.findByUsername("igordeoliveirasa@gmail.com")).thenReturn(null);
            
            indexController.loginCallback("codeXYZ");
            class PlayerMatcher extends ArgumentMatcher<Player> {

                @Override
                public boolean matches(Object argument) {
                    Player player = (Player)argument;
                    return  player.getCredentials().getUsername().equals("igordeoliveirasa@gmail.com")&&
                            player.getCredentials().getPassword().equals("123") && 
                            player.getCredentials().getId()==null &&
                            player.getName().equals("Igor de Oliveira Sá");
                }
                
            }
            
            verify(playerRepository, times(1)).create(argThat(new PlayerMatcher()));
            
        } catch (FacebookException ex) {
            fail();
        }
    }
    
    @Test
    public void loginCallbackNotRegisteredThrowingException() {
        try {
            when(facebook.getMe()).thenThrow(FacebookException.class);
            when(sessionManager.getFacebook()).thenReturn(facebook);
            indexController.loginCallback("codeXYZ");            
            verify(sessionManager, never()).signIn(any(Player.class));
        } catch (FacebookException ex) {
            fail();
        }
    }
    
    @Test
    public void index() {
        when(sessionManager.getPlayer()).thenReturn(new Player.Builder().withId(2L).build());
        List<Player> players = new ArrayList<Player>();
        players.add(new Player.Builder().withId(1L).build());
        players.add(new Player.Builder().withId(2L).build());
        players.add(new Player.Builder().withId(3L).build());
        players.add(new Player.Builder().withId(4L).build());
        when(playerRepository.findAll()).thenReturn(players);
        indexController.index();
        assertEquals(((List<Player>)result.included().get("players")).get(0).getId(), 1L, 0);
        assertEquals(((List<Player>)result.included().get("players")).get(1).getId(), 3L, 0);
        assertEquals(((List<Player>)result.included().get("players")).get(2).getId(), 4L, 0);
    }
}