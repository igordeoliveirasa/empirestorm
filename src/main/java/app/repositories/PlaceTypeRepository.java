package app.repositories;

import java.util.List;

import app.models.PlaceType;

public interface PlaceTypeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PlaceType entity);
	
	PlaceType update(PlaceType entity);
	
	void destroy(PlaceType entity);
	
	PlaceType find(Long id);
	
	List<PlaceType> findAll();

}
