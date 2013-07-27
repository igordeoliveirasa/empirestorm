package app.repositories;

import java.util.List;

import app.models.Place;

public interface PlaceRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Place entity);
	
	Place update(Place entity);
	
	void destroy(Place entity);
	
	Place find(Long id);
        Place findByName(String name);
	
	List<Place> findAll();

}
