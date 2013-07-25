package app.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FeedbackRepositoryImplTest {

    @Test public void fakeTest() {
  		assertNotNull("put something real.", new FeedbackRepositoryImpl(null));
  	}
}

