/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.components;

import app.controllers.*;
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
import app.repositories.PlayerActionWalkRepository;
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
public class PlayerFactoryTest {
    
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
        PlayerActionFactory playerActionFactory = new PlayerActionFactory();
    }

    @Test
    public void buildTravelingWalking() {
        cú!
        playerActionFactory.buildTravelingWalking(null, null, null);
    }
        
}