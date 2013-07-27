package app.repositories;

import java.util.List;

import app.models.PlayerSkills;

public interface PlayerSkillsRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PlayerSkills entity);
	
	PlayerSkills update(PlayerSkills entity);
	
	void destroy(PlayerSkills entity);
	
	PlayerSkills find(Long id);
	
	List<PlayerSkills> findAll();

}
