package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayerStateControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new PlayerStateController(null, null, null));
 	}
}
