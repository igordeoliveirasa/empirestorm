package app.controllers;

import app.components.MessagesProperties;
import app.components.PlayerActionFactory;
import app.models.Place;
import app.models.Player;
import app.models.PlayerActionWalk;
import app.repositories.PlaceRepository;
import app.repositories.PlaceTypeRepository;
import app.repositories.PlayerActionWalkRepository;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import facebook4j.Facebook;
import facebook4j.User;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerControllerTest {

    private PlayerController playerController;
    
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
    
    
    @Before
    public void setUp() {
        validator = new MockValidator();
        result = new MockResult();
        messagesProperties = new MessagesProperties();
        MockitoAnnotations.initMocks(this);
        playerController = new PlayerController(result, playerRepository, validator, playerActionWalkRepository, playerActionFactory, sessionManager, placeRepository);
    }
    
    @Test 
    public void actionWalkTo() {
        Place currentPlace = new Place.Builder().withId(1L).withX(0).withY(0).build();
        Player player = new Player.Builder().withId(1L).withPlace(currentPlace).build();
        
        when(sessionManager.getPlayer()).thenReturn(player);
        when(playerActionWalkRepository.get()).thenReturn(null);
        when(playerRepository.find(1L)).thenReturn(player);
        
        Place toPlace = new Place.Builder().withId(2L).withX(1).withY(1).build();
        when(placeRepository.find(2L)).thenReturn(toPlace);
        
        PlayerActionWalk playerActionWalk = new PlayerActionWalk.Builder().withPlayer(player).withFromPlace(currentPlace).withToPlace(toPlace).build();
        when(playerActionFactory.buildTravelingWalking(eq(player), eq(currentPlace), eq(toPlace))).thenReturn(playerActionWalk);        
        playerController.actionWalkTo(2L);
        
        class PlayerActionWalkMatcher extends ArgumentMatcher<PlayerActionWalk> {

            @Override
            public boolean matches(Object argument) {
                PlayerActionWalk playerActionWalk = (PlayerActionWalk) argument;
                return playerActionWalk.getPlayer().getId().compareTo(1L) == 0 &&
                        playerActionWalk.getFromPlace().getId().compareTo(1L) == 0 &&
                        playerActionWalk.getToPlace().getId().compareTo(2L) == 0 &&
                        playerActionWalk.getDurationInMinutes() == 1.41;
            }
            
        }
        
        verify(playerActionWalkRepository).create(argThat(new PlayerActionWalkMatcher()));
    }
    
    @Test 
    public void actionStopWalkTo() {        
        PlayerActionWalk playerActionWalk = new PlayerActionWalk.Builder().withId(2L).build();
        when(playerActionWalkRepository.get()).thenReturn(playerActionWalk);
        playerController.actionStopWalkTo();
        
        class PlayerActionWalkMatcher extends ArgumentMatcher<PlayerActionWalk> {

            @Override
            public boolean matches(Object argument) {
                PlayerActionWalk playerActionWalk = (PlayerActionWalk) argument;
                return playerActionWalk.getId().compareTo(2L) == 0;
            }
            
        }
        
        verify(playerActionWalkRepository).destroy(argThat(new PlayerActionWalkMatcher()));
    }    
}
