package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayerSkillsControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new PlayerSkillsController(null, null, null));
 	}
}
