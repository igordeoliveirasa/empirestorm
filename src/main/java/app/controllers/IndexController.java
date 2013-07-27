package app.controllers;



import app.interceptors.SignedInPlayerNotImportant;
import app.models.Player;
import app.models.PlayerCredentials;
import app.repositories.PlayerCredentialsRepository;
import app.repositories.PlayerRepository;
import app.session.SessionManager;
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

@Resource
public class IndexController {

	private final Result result;
	private final Validator validator;
        private SessionManager sessionManager;
        private PlayerCredentialsRepository playerCredentialsRepository;
        private PlayerRepository playerRepository;
        
	public IndexController(Result result, Validator validator, SessionManager sessionManager, PlayerCredentialsRepository playerCredentialsRepository, PlayerRepository playerRepository) {
            this.result = result;
            this.validator = validator;
            this.sessionManager = sessionManager;
            this.playerCredentialsRepository = playerCredentialsRepository;
            this.playerRepository = playerRepository;
            
            result.include("sm", sessionManager);
	}
	
        @Post
        @Get
        @Path("/")
        @SignedInPlayerNotImportant
        public void login() {
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
            List<Player> players = new ArrayList<Player>();
            for (Player player: playerRepository.findAll()) {
                if (player.getId().compareTo(sessionManager.getPlayer().getId())!=0) {
                    players.add(player);
                }
            }
            result.include("players", players);
        }	
}