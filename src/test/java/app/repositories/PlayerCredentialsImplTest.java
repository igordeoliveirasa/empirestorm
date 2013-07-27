package app.repositories;

import app.models.Player;
import app.models.PlayerCredentials;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

public class PlayerCredentialsImplTest {
    
    
    private PlayerCredentialsRepository repository;
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private Query query;
            
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new PlayerCredentialsRepositoryImpl(entityManager);
    }

    
    @Test 
    public void create() {
        Player player = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(null).build();
        PlayerCredentials entity = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        entity.setPlayer(player);
        player.setCredentials(entity);
        
        repository.create(entity);
        
        class EntityMatcher extends ArgumentMatcher<PlayerCredentials> {

            @Override
            public boolean matches(Object argument) {
                PlayerCredentials entity = (PlayerCredentials)argument;
                return  entity.getUsername().equals("igordeoliveirasa@gmail.com") &&
                        entity.getPassword().equals("123");
            }
            
        }
        
        verify(entityManager).persist(argThat(new EntityMatcher()));
    }
    
    @Test 
    public void update() {
        Player player = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(null).build();
        PlayerCredentials entity = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        entity.setPlayer(player);
        player.setCredentials(entity);
        
        repository.update(entity);
        
        class EntityMatcher extends ArgumentMatcher<PlayerCredentials> {

            @Override
            public boolean matches(Object argument) {
                PlayerCredentials entity = (PlayerCredentials)argument;
                return  entity.getUsername().equals("igordeoliveirasa@gmail.com") &&
                        entity.getPassword().equals("123");
            }
            
        }
        
        verify(entityManager).merge(argThat(new EntityMatcher()));
    }
    
    @Test 
    public void destroy() {
        Player player = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(null).build();
        PlayerCredentials entity = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        entity.setPlayer(player);
        player.setCredentials(entity);
        
        repository.destroy(entity);
        
        class EntityMatcher extends ArgumentMatcher<PlayerCredentials> {

            @Override
            public boolean matches(Object argument) {
                PlayerCredentials entity = (PlayerCredentials)argument;
                return  entity.getUsername().equals("igordeoliveirasa@gmail.com") &&
                        entity.getPassword().equals("123");
            }
            
        }
        
        verify(entityManager).remove(argThat(new EntityMatcher()));
    }

    @Test 
    public void find() {
        
        repository.find(1L);
        
        class EntityMatcher extends ArgumentMatcher<Long> {

            @Override
            public boolean matches(Object argument) {
                Long id = (Long)argument;
                return id.compareTo(1L) == 0;
            }
            
        }
        
        verify(entityManager).find(PlayerCredentials.class, 1L);
    }

    @Test
    public void findAll() {
        when(query.getResultList()).thenReturn(new ArrayList());
        when(entityManager.createQuery(anyString())).thenReturn(query);
        repository.findAll();        
        verify(entityManager).createQuery(eq("from app.models.PlayerCredentials"));
    }
    
    @Test
    public void findByUsername() {
        List<PlayerCredentials> playerCredentialses = new ArrayList<PlayerCredentials>();
        playerCredentialses.add(new PlayerCredentials.Builder().withUsername("igordeoliveirasa@gmail.com").build());
        
        when(query.getResultList()).thenReturn(playerCredentialses);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        
        PlayerCredentials playerCredentials = repository.findByUsername("igordeoliveirasa@gmail.com");
        
        assertEquals(playerCredentials.getUsername(), "igordeoliveirasa@gmail.com");
        
        verify(query).setParameter("username", "igordeoliveirasa@gmail.com");
        verify(entityManager).createQuery(eq("from app.models.PlayerCredentials e where upper(e.username) = upper(:username)"));
    }
    
    @Test
    public void findByUsernamInvalid() {        
        when(query.getResultList()).thenReturn(new ArrayList());
        when(entityManager.createQuery(anyString())).thenReturn(query);
        
        PlayerCredentials playerCredentials = repository.findByUsername("igordeoliveirasa@gmail.com");
        
        assertNull(playerCredentials);        
        verify(query).setParameter("username", "igordeoliveirasa@gmail.com");
        verify(entityManager).createQuery(eq("from app.models.PlayerCredentials e where upper(e.username) = upper(:username)"));
    }    
}

