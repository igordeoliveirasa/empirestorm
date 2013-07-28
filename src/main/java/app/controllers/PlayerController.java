package app.controllers;

import app.components.PlayerActionFactory;
import app.models.Place;
import java.util.List;

import app.models.Player;
import app.models.PlayerActionWalk;
import app.repositories.PlaceRepository;
import app.repositories.PlayerActionWalkRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import com.google.common.base.Strings;

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

    @Get("/players")
    public List<Player> index() {
            return playerRepository.findAll();
    }

    @Post("/players")
    public void create(Player player) {
            validator.validate(player);
            validator.onErrorUsePageOf(this).newPlayer();
            playerRepository.create(player);
            result.redirectTo(this).index();
    }

    @Get("/players/new")
    public Player newPlayer() {
            return new Player();
    }

    @Put("/players")
    public void update(Player player) {
            validator.validate(player);
            validator.onErrorUsePageOf(this).edit(player);
            playerRepository.update(player);
            result.redirectTo(this).index();
    }

    @Get("/players/{player.id}/edit")
    public Player edit(Player player) {

            return playerRepository.find(player.getId());
    }

    @Get("/players/{player.id}")
    public Player show(Player player) {
            return playerRepository.find(player.getId());
    }

    @Delete("/players/{player.id}")
    public void destroy(Player player) {
            playerRepository.destroy(playerRepository.find(player.getId()));
            result.redirectTo(this).index();  
    }

    @Get("/player/action/walkTo/{placeId}")
    public void actionWalkTo(Long placeId) {
        
        final boolean isBusy = playerActionWalkRepository.findAllNotFinalized().size()!=0;
        validator.checking(new Validations() { {
            that(!isBusy, "action.busy", "action.busy");
        } });            
        validator.onErrorForwardTo(IndexController.class).index();

        
        
        Player player = playerRepository.find(sessionManager.getPlayer().getId());
        Place place = placeRepository.find(placeId);
        PlayerActionWalk playerActionWalk = playerActionFactory.buildTravelingWalking(player, player.getPlace(), place);
        playerActionWalkRepository.create(playerActionWalk);
        result.redirectTo(IndexController.class).index();
    }
}