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

public class PlayerRepositoryImplTest {
    
    
    private PlayerRepository repository;
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private Query query;
            
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new PlayerRepositoryImpl(entityManager);
    }

    
    @Test 
    public void create() {
        PlayerCredentials playerCredentials = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        Player entity = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(playerCredentials).build();
        playerCredentials.setPlayer(entity);
        
        repository.create(entity);
        
        class EntityMatcher extends ArgumentMatcher<Player> {

            @Override
            public boolean matches(Object argument) {
                Player entity = (Player)argument;
                return entity.getName().equals("Igor de Oliveira Sá");
            }
            
        }
        
        verify(entityManager).persist(argThat(new EntityMatcher()));
    }
    
    @Test 
    public void update() {
        PlayerCredentials playerCredentials = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        Player entity = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(playerCredentials).build();
        playerCredentials.setPlayer(entity);
        
        repository.update(entity);
        
        class EntityMatcher extends ArgumentMatcher<Player> {

            @Override
            public boolean matches(Object argument) {
                Player entity = (Player)argument;
                return entity.getName().equals("Igor de Oliveira Sá");
            }
            
        }
        
        verify(entityManager).merge(argThat(new EntityMatcher()));
    }
    
    @Test 
    public void destroy() {
        PlayerCredentials playerCredentials = new PlayerCredentials.Builder().withPassword("123").withUsername("igordeoliveirasa@gmail.com").withPlayer(null).build();
        Player entity = new Player.Builder().withName("Igor de Oliveira Sá").withCredentials(playerCredentials).build();
        playerCredentials.setPlayer(entity);
        
        repository.destroy(entity);
        
        class EntityMatcher extends ArgumentMatcher<Player> {

            @Override
            public boolean matches(Object argument) {
                Player entity = (Player)argument;
                return entity.getName().equals("Igor de Oliveira Sá");
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
        
        verify(entityManager).find(Player.class, 1L);
    }

    @Test
    public void findAll() {
        when(query.getResultList()).thenReturn(new ArrayList());
        when(entityManager.createQuery(anyString())).thenReturn(query);
        repository.findAll();        
        verify(entityManager).createQuery(eq("from app.models.Player"));
    }
}

