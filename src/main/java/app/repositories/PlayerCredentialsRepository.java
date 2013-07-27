package app.repositories;

import java.util.List;

import app.models.PlayerCredentials;

public interface PlayerCredentialsRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PlayerCredentials entity);
	
	PlayerCredentials update(PlayerCredentials entity);
	
	void destroy(PlayerCredentials entity);
	
	PlayerCredentials find(Long id);
	
	List<PlayerCredentials> findAll();
        PlayerCredentials findByUsername(String username);

}
