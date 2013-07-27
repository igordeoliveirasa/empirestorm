package app.repositories;

import java.util.List;

import app.models.Player;

public interface PlayerRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Player entity);
	
	Player update(Player entity);
	
	void destroy(Player entity);
	
	Player find(Long id);
	
	List<Player> findAll();

}
