package app.repositories;

import java.util.List;

import app.models.PlayerActionWalk;

public interface PlayerActionWalkRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PlayerActionWalk entity);
	
	PlayerActionWalk update(PlayerActionWalk entity);
	
	void destroy(PlayerActionWalk entity);
	
	PlayerActionWalk find(Long id);
	
	List<PlayerActionWalk> findAll();

}
