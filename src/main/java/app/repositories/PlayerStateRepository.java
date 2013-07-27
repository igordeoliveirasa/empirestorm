package app.repositories;

import java.util.List;

import app.models.PlayerState;

public interface PlayerStateRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PlayerState entity);
	
	PlayerState update(PlayerState entity);
	
	void destroy(PlayerState entity);
	
	PlayerState find(Long id);
	
	List<PlayerState> findAll();

}
