package app.controllers;

import app.components.PlayerActionFactory;
import app.models.Place;

import app.models.Player;
import app.models.PlayerActionWalk;
import app.repositories.PlaceRepository;
import app.repositories.PlayerActionWalkRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import java.util.Date;

@Resource
public class PlayerController {

    private final Result result;
    private final PlayerRepository playerRepository;

    private final Validator validator;
    private final PlayerActionWalkRepository playerActionWalkRepository;
    private final PlayerActionFactory playerActionFactory;
    private final SessionManager sessionManager;
    private final PlaceRepository placeRepository;

    public PlayerController(Result result, PlayerRepository repository, 
    Validator validator, PlayerActionWalkRepository playerActionWalkRepository,
    PlayerActionFactory playerActionFactory, SessionManager sessionManager, PlaceRepository placeRepository) {
            this.result = result;
            this.playerRepository = repository;
            this.validator = validator;
            this.playerActionWalkRepository = playerActionWalkRepository;
            this.playerActionFactory = playerActionFactory;
            this.sessionManager = sessionManager;
            this.placeRepository = placeRepository;
    }

    @Get("/player/action/walkTo/{placeId}")
    public void actionWalkTo(Long placeId) {
        
        final boolean isBusy = playerActionWalkRepository.get() != null;
        validator.checking(new Validations() { {
            that(!isBusy, "action.busy", "action.busy");
        } });            
        validator.onErrorForwardTo(IndexController.class).index();
        
        Player player = playerRepository.find(sessionManager.getPlayer().getId());
        Place place = placeRepository.find(placeId);
        PlayerActionWalk playerActionWalk = playerActionFactory.buildTravelingWalking(player, player.getPlace(), place);
        playerActionWalk.setCreatedAt(new Date());
        playerActionWalkRepository.create(playerActionWalk);
        result.redirectTo(IndexController.class).index();
    }
    
    @Get("/player/action/walkTo/stop")
    public void actionStopWalkTo() {
        PlayerActionWalk playerActionWalk = playerActionWalkRepository.get();
        if (playerActionWalk!=null) {
            playerActionWalkRepository.destroy(playerActionWalk);
        }
        result.redirectTo(IndexController.class).index();
    }
}