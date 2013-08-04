package app.controllers;



import app.interceptors.SignedInPlayerNotImportant;
import app.models.Place;
import app.models.PlaceType;
import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlaceRepository;
import app.repositories.PlaceTypeRepository;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
import app.components.MessagesProperties;
import app.components.PlayerActionFactory;
import app.models.PlayerActionWalk;
import app.repositories.PlayerActionWalkRepository;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import facebook4j.FacebookException;
import facebook4j.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Resource
public class IndexController {

	private final Result result;
	private final Validator validator;
        private SessionManager sessionManager;
        private PlayerCredentialsRepository playerCredentialsRepository;
        private PlayerRepository playerRepository;
        private MessagesProperties messagesProperties;
        private PlaceTypeRepository placeTypeRepository;
        private PlaceRepository placeRepository;
        private PlayerActionFactory playerActionFactory;
        private PlayerActionWalkRepository playerActionWalkRepository;
        
	public IndexController(Result result, Validator validator, SessionManager sessionManager, PlayerCredentialsRepository playerCredentialsRepository, PlayerRepository playerRepository, MessagesProperties messagesProperties, PlaceTypeRepository placeTypeRepository, PlaceRepository placeRepository, PlayerActionFactory playerActionFactory, PlayerActionWalkRepository playerActionWalkRepository) {
            this.result = result;
            this.validator = validator;
            this.sessionManager = sessionManager;
            this.playerCredentialsRepository = playerCredentialsRepository;
            this.playerRepository = playerRepository;
            this.messagesProperties = messagesProperties;
            this.placeTypeRepository = placeTypeRepository;
            this.placeRepository = placeRepository;
            this.playerActionFactory = playerActionFactory;
            this.playerActionWalkRepository = playerActionWalkRepository;
            
            result.include("sm", sessionManager);
	}
	
        @Post
        @Get
        @Path("/")
        @SignedInPlayerNotImportant
        public void login() {
            
            if (playerRepository.findAll().size()==0) {
            
                PlaceType placeTypeLake = new PlaceType.Builder().withName("Lago").build();
                placeTypeRepository.create(placeTypeLake);
                
                PlaceType placeTypeForest = new PlaceType.Builder().withName("Floresta Fechada").build();
                placeTypeRepository.create(placeTypeForest);
                
                PlaceType placeTypeField = new PlaceType.Builder().withName("Campo aberto e gramado").build();
                placeTypeRepository.create(placeTypeField);
                
                PlaceType placeTypeMountain = new PlaceType.Builder().withName("Montanha").build();
                placeTypeRepository.create(placeTypeMountain);
                
                
                Place lakeland = new Place.Builder().withName("Lakeland").withType(placeTypeLake).withX(5).withY(5).build();
                placeRepository.create(lakeland);
                
                Place borealForest = new Place.Builder().withName("Boreal").withType(placeTypeForest).withX(20).withX(20).build();
                placeRepository.create(borealForest);
                
                Place stormField = new Place.Builder().withName("Storm Field").withType(placeTypeField).withX(0).withX(0).build();
                placeRepository.create(stormField);
                
                Place stormMountain = new Place.Builder().withName("Storm Mountain").withType(placeTypeMountain).withX(1).withX(1).build();
                placeRepository.create(stormMountain);

                Place dragonMountain = new Place.Builder().withName("Dragon Mountain").withType(placeTypeMountain).withX(800).withX(800).build();
                placeRepository.create(dragonMountain);
            }
            
            result.redirectTo(sessionManager.getFacebook().getOAuthAuthorizationURL("http://localhost:8080/loginCallback"));
        }
        
        @Post
        @Get
        @Path("/loginCallback")
        @SignedInPlayerNotImportant
        public void loginCallback(String code) {
            try {
                sessionManager.getFacebook().getOAuthAccessToken(code);
                User user = sessionManager.getFacebook().getMe();
                PlayerCredentials playerCredentials = playerCredentialsRepository.findByUsername(user.getEmail());
                
                if (playerCredentials==null) {
                    playerCredentials = new PlayerCredentials();
                    playerCredentials.setUsername(user.getEmail());
                    playerCredentials.setPassword("123");
                    
                    Player player = new Player();
                    player.setName(user.getName());
                    
                    // relationship
                    player.setCredentials(playerCredentials);
                    playerCredentials.setPlayer(player);
                    
                    player.setPlace(placeRepository.findByName("Storm Field"));
                    
                    playerRepository.create(player);
                }
                sessionManager.signIn(playerCredentials.getPlayer());
                result.redirectTo(this).index();
            } catch (FacebookException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                result.redirectTo(this).login();
            }
        }
        
        
        
        
        @Post
        @Get
        @Path("/index")
        public void index() {
            Player player = sessionManager.getPlayer(playerRepository);
            
            // checking finalized progress
            finalizeCompletedActions(player);
//            List<PlayerActionWalk> notFinalizedWalks = playerActionWalkRepository.findAllNotFinalized();
//            for (PlayerActionWalk action : notFinalizedWalks) {
//                if (action.getProgressValue()>=1.0) {
//                    action.setFinalized(true);
//                    playerActionWalkRepository.update(action);
//                    player.setPlace(action.getToPlace());
//                    playerRepository.update(player);
//                }
//            }
//            
            
            // informing actions in progress
            List<PlayerActionWalk> playerActionsWalkInProgress = playerActionWalkRepository.findAllNotFinalized();
            result.include("actionsWalkInProgress", playerActionsWalkInProgress);
            
            // informing places to walk
            //List<Place> places = placeRepository.findAll();
            //places.remove(player.getPlace());
            //List<PlayerActionWalk> playerActionWalks = playerActionFactory.buildTravelingWalking(player, player.getPlace(), places);
            List<PlayerActionWalk> playerActionWalks = getAvailablePlacesToWalk(player);
            result.include("actionsWalk", playerActionWalks);

            // listing statistics
            List<Player> players = new ArrayList<Player>();
            for (Player p: playerRepository.findAll()) {
                if (p.getId().compareTo(player.getId())!=0) {
                    players.add(p);
                }
            }
            result.include("players", players);
        }	

    public void finalizeCompletedActions(Player player) {        
        // checking finalized progress
        List<PlayerActionWalk> notFinalizedWalks = playerActionWalkRepository.findAllNotFinalized();
        for (PlayerActionWalk action : notFinalizedWalks) {
            if (action.getProgressValue()>=1.0) {
                action.setFinalized(true);
                playerActionWalkRepository.update(action);
                player.setPlace(action.getToPlace());
                playerRepository.update(player);
            }
        }
    }

    public List<PlayerActionWalk> getAvailablePlacesToWalk(Player player) {
        List<Place> places = placeRepository.findAll();
        places.remove(player.getPlace());
        return playerActionFactory.buildTravelingWalking(player, player.getPlace(), places);        
    }
}