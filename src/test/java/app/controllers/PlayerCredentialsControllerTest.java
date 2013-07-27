package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayerCredentialsControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new PlayerCredentialsController(null, null, null));
 	}
}
