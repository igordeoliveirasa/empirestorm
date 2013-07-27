package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayerControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new PlayerController(null, null, null));
 	}
}
