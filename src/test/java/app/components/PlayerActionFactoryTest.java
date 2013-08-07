/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.components;

import app.models.Player;
import app.repositories.PlaceRepository;
import app.repositories.PlaceTypeRepository;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import app.models.Place;
import app.models.PlayerActionWalk;
import app.repositories.PlayerActionWalkRepository;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author igor
 */
public class PlayerActionFactoryTest {
    
    private Validator validator;
    private Result result;
    
    private Result spyResult;
    
    @Mock
    private SessionManager sessionManager;
    
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
    private PlayerActionWalkRepository playerActionWalkRepository;
    
    private PlayerActionFactory playerActionFactory;
            
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playerActionFactory = new PlayerActionFactory();
    }

    @Test
    public void buildTravelingWalking() {
        Place fromPlace = new Place.Builder().withId(1L).withName("From Place").withX(0).withY(0).build();
        Place toPlace = new Place.Builder().withId(3L).withName("To Place").withX(1).withY(1).build();
        Player player = new Player.Builder().withId(2L).withPlace(fromPlace).build();

        PlayerActionWalk playerActionWalk = playerActionFactory.buildTravelingWalking(player, fromPlace, toPlace);
        assertEquals(playerActionWalk.getPlayer().getId(), 2L, 0);
        assertEquals(playerActionWalk.getFromPlace().getId(), 1L, 0);
        assertEquals(playerActionWalk.getToPlace().getId(), 3L, 0);
        assertEquals(playerActionWalk.getDistance(), 1.41, 0);
    }
        
}