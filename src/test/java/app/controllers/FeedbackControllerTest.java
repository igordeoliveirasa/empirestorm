package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FeedbackControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new FeedbackController(null, null, null, null, null));
 	}
}
