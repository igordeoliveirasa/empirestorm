package app.controllers;

import app.models.Feedback;
import app.models.Player;
import app.repositories.FeedbackRepository;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import facebook4j.Facebook;
import facebook4j.User;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.MockitoAnnotations;

public class FeedbackControllerTest {
    
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
    
    @Mock
    private FeedbackRepository feedbackRepository;
    
    private FeedbackController feedbackController;
            
    @Before
    public void setUp() {
        validator = new MockValidator();
        result = new MockResult();
        MockitoAnnotations.initMocks(this);
        feedbackController = new FeedbackController(result, feedbackRepository, validator, sessionManager, playerRepository);
    }
    
    @Test 
    public void newFeedback() {
        Feedback result = feedbackController.newFeedback();
        assertNotNull(result);
    }
    
    @Test 
    public void create() {
        Player player = new Player.Builder().withId(1L).build();
        when(sessionManager.getPlayer()).thenReturn(player);
        when(playerRepository.find(1L)).thenReturn(player);
        
        Feedback feedback = new Feedback.Builder().withId(0L).withPlayer(player).withMessage("That's my feedback!").build();
        
        feedbackController.create(feedback);
        
        class FeedbackMatcher extends ArgumentMatcher<Feedback> {

            @Override
            public boolean matches(Object argument) {
                Feedback feedback = (Feedback)argument;
                return  feedback.getMessage().equals("That's my feedback!") &&
                        feedback.getPlayer().getId().compareTo(1L) == 0;
            }
            
        }
        
        verify(feedbackRepository).create(argThat(new FeedbackMatcher()));
    }

    @Test 
    public void createEmpty() {
        try 
        {
            Player player = new Player.Builder().withId(1L).build();
            when(sessionManager.getPlayer()).thenReturn(player);
            when(playerRepository.find(1L)).thenReturn(player);

            Feedback feedback = new Feedback.Builder().withId(0L).withPlayer(player).withMessage("").build();

            feedbackController.create(feedback);
            fail();
        } 
        catch (ValidationException e)
        {
            
        }
    }
}
