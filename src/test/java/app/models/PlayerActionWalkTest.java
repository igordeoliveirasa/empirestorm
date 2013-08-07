package app.models;

import app.session.SessionManager;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class PlayerActionWalkTest {
    
    PlayerActionWalk playerActionWalk;
    
    @Before
    public void setUp() {
        playerActionWalk = new PlayerActionWalk();
    }

    @Test 
    public void getFormattedDistance() {
        playerActionWalk.setFromPlace(new Place.Builder().withX(0).withY(0).build());
        playerActionWalk.setToPlace(new Place.Builder().withX(1).withY(1).build());
        assertEquals(playerActionWalk.getFormattedDistance(), "1.41 kms");
    }
    
    @Test 
    public void getFormattedDuration() {
        playerActionWalk.setPlayer(new Player.Builder().withSkills(new PlayerSkills.Builder().withWalkVelocity(1).build()).build());
        playerActionWalk.setFromPlace(new Place.Builder().withX(0).withY(0).build());
        playerActionWalk.setToPlace(new Place.Builder().withX(1).withY(1).build());
        assertEquals(playerActionWalk.getFormattedDuration(), "1.41 mins");
    }
    
    @Test 
    public void getCurrentTime() {
        try {
            long time = playerActionWalk.getCurrentTime();
            Thread.sleep(500);
            assertTrue(playerActionWalk.getCurrentTime()>time);
        } catch (InterruptedException ex) {
        }
    }
}

