package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlaceControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new PlaceController(null, null, null));
 	}
}
