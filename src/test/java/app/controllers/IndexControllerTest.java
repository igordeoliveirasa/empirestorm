/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlaceRepository;
import app.repositories.PlaceTypeRepository;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import app.components.MessagesProperties;
import app.components.PlayerActionFactory;
import app.models.Place;
import app.models.PlayerActionWalk;
import app.models.PlayerSkills;
import app.repositories.PlayerActionWalkRepository;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import static org.junit.Assert.*;
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
    
    private MessagesProperties messagesProperties;
    
    @Mock
    private PlaceRepository placeRepository;
    
    @Mock
    private PlaceTypeRepository placeTypeRepository;
    
    @Mock
    private PlayerActionFactory playerActionFactory;
    
    @Mock
    private PlayerActionWalkRepository playerActionWalkRepository;
    
    private IndexController indexController;
            
    @Before
    public void setUp() {
        validator = new MockValidator();
        result = new MockResult();
        messagesProperties = new MessagesProperties();
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(result, validator, sessionManager, playerCredentialsRepository, playerRepository, messagesProperties, placeTypeRepository, placeRepository, playerActionFactory, playerActionWalkRepository);
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
        Place place = new Place.Builder().build();
        Player player = new Player.Builder().withId(2L).withPlace(place).build();
        
        when(sessionManager.getPlayer(eq(playerRepository))).thenReturn(player);
        
        List<Player> players = new ArrayList<Player>();
        players.add(new Player.Builder().withId(1L).build());
        players.add(new Player.Builder().withId(2L).build());
        players.add(new Player.Builder().withId(3L).build());
        players.add(new Player.Builder().withId(4L).build());
        when(playerRepository.findAll()).thenReturn(players);
        
        List<Place> places = new ArrayList<Place>();
        places.add(new Place.Builder().withName("A").build());
        places.add(new Place.Builder().withName("B").build());
        places.add(new Place.Builder().withName("C").build());
        places.add(new Place.Builder().withName("D").build());
        
        when(placeRepository.findAll()).thenReturn(places);
        
        List<PlayerActionWalk> playerActionsWalk = new ArrayList<PlayerActionWalk>();
        playerActionsWalk.add(new PlayerActionWalk.Builder().build());
        playerActionsWalk.add(new PlayerActionWalk.Builder().build());
        playerActionsWalk.add(new PlayerActionWalk.Builder().build());
        playerActionsWalk.add(new PlayerActionWalk.Builder().build());
        
        when(playerActionFactory.buildTravelingWalking(eq(player), eq(place), eq(places))).thenReturn(playerActionsWalk);
        indexController.index();
        
        assertEquals(((List<Player>)result.included().get("players")).get(0).getId(), 1L, 0);
        assertEquals(((List<Player>)result.included().get("players")).get(1).getId(), 3L, 0);
        assertEquals(((List<Player>)result.included().get("players")).get(2).getId(), 4L, 0);
    }
    
    
    @Test
    public void finalizeCompletedActions() {
        Player player = new Player.Builder().build();
        when(playerActionWalkRepository.get()).thenReturn(null);
        indexController.finalizeCompletedActions(player);
        verify(playerActionWalkRepository).get();
    }
    
    @Test
    public void finalizeCompletedActions2() {
        Player player = new Player.Builder().withSkills(new PlayerSkills.Builder().withWalkVelocity(1L).build()).build();
        
        Place fromPlace = new Place.Builder().withId(1L).withName("From Place").withX(0).withY(0).build();
        Place toPlace = new Place.Builder().withId(3L).withName("To Place").withX(1).withY(1).build();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        
        PlayerActionWalk notFinalizedWalk = new PlayerActionWalk.Builder().withPlayer(player).withCreatedAt(calendar.getTime()).withFromPlace(fromPlace).withToPlace(toPlace).withFinalized(false).build();
        
        when(playerActionWalkRepository.get()).thenReturn(notFinalizedWalk);
        indexController.finalizeCompletedActions(player);
        verify(playerActionWalkRepository).get();
        
        
        class PlayerActionWalkMatcher extends ArgumentMatcher<PlayerActionWalk> {

            @Override
            public boolean matches(Object argument) {
                PlayerActionWalk playerActionWalk = (PlayerActionWalk)argument;
                return playerActionWalk.isFinalized();
            }
        }
        verify(playerActionWalkRepository).update(argThat(new PlayerActionWalkMatcher()));
        
        class PlayerMatcher extends ArgumentMatcher<Player> {

            @Override
            public boolean matches(Object argument) {
                Player player = (Player)argument;
                return player.getPlace().getId().compareTo(3L)==0;
            }
        }
        verify(playerRepository).update(argThat(new PlayerMatcher()));
    }
    
    
    @Test
    public void getAvailablePlacesToWalk() {
        Place currentPlace = new Place.Builder().withId(1L).withName("Storm Field").build();
        Player player = new Player.Builder().withId(2L).withPlace(currentPlace).build();
        
        List<Place> places = new ArrayList<Place>();
        places.add(new Place.Builder().withId(2L).withName("A").build());
        places.add(new Place.Builder().withId(3L).withName("B").build());
        places.add(currentPlace);
        places.add(new Place.Builder().withId(4L).withName("C").build());

        
        when(placeRepository.findAll()).thenReturn(places);
        
        List<PlayerActionWalk> playerActionsWalk = indexController.getAvailablePlacesToWalk(player);
        
        
        class PlacesMatcher extends ArgumentMatcher<List<Place>> {
            @Override
            public boolean matches(Object argument) {
                List<Place> places = (List<Place>) argument;
                return  places.get(0).getName().equals("A") &&
                        places.get(1).getName().equals("B") &&
                        places.get(2).getName().equals("C");
            }
        }
        
        verify(playerActionFactory).buildTravelingWalking(eq(player), eq(currentPlace), argThat(new PlacesMatcher()));
        //assertEquals(playerActionsWalk.get(0).getFromPlace().getName(), "Storm Field");
        //assertEquals(playerActionsWalk.get(0).getToPlace().getName(), "A");
        //assertEquals(playerActionsWalk.get(1).getToPlace().getName(), "B");
        //assertEquals(playerActionsWalk.get(2).getToPlace().getName(), "C");
    }
    
}