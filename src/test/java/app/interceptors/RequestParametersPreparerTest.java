/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.interceptors;

import app.controllers.*;
import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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
public class RequestParametersPreparerTest {
    
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
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private InterceptorStack stack;
    @Mock
    private ResourceMethod resourceMethod;
    
    @Mock
    private MutableRequest mutableRequest;
    
    private RequestParametersPreparer requestParametersPreparer;
            
    @Before
    public void setUp() {
        validator = new MockValidator();
        result = new MockResult();
        MockitoAnnotations.initMocks(this);
        requestParametersPreparer = new RequestParametersPreparer(mutableRequest, sessionManager, request);
    }

    @Test
    public void interceptValidCharacters() {
        
        Object object = new Object();
        
        Map map = new HashMap();
        map.put("key1", new String[] {"value1"});
        map.put("key2", new String[] {"value2"});
        map.put("key3", new String[] {"value3"});
        
        when(mutableRequest.getParameterMap()).thenReturn(map);
        requestParametersPreparer.intercept(stack, resourceMethod, object);
        verify(stack).next(eq(resourceMethod), eq(object));
    }
    
    @Test
    public void interceptInvalidCharacters() {
        
        Object object = new Object();
        
        Map map = new HashMap();
        map.put("key1", new String[] {"value1"});
        map.put("key2", new String[] {"<script>Olá Mundo!</script>"});
        map.put("key3", new String[] {"value3"});
        
        when(mutableRequest.getParameterMap()).thenReturn(map);
        requestParametersPreparer.intercept(stack, resourceMethod, object);
        verify(mutableRequest).setParameter("key2", "");
        verify(stack).next(eq(resourceMethod), eq(object));
    }
    
    @Test
    public void acceptsSuccessfuly() {
        boolean result = requestParametersPreparer.accepts(resourceMethod);
        assertTrue(result);
    }
}