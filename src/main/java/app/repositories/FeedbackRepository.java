package app.repositories;

import java.util.List;

import app.models.Feedback;

public interface FeedbackRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Feedback entity);
	
	Feedback update(Feedback entity);
	
	void destroy(Feedback entity);
	
	Feedback find(Long id);
	
	List<Feedback> findAll();

}
