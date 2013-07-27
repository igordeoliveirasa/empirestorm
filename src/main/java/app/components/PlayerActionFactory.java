/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.components;

import app.models.Place;
import app.models.Player;
import app.models.PlayerActionWalk;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
@Component
@ApplicationScoped
public class PlayerActionFactory {
        
    public PlayerActionWalk buildTravelingWalking(Player player, Place destination) {
        
        PlayerActionWalk playerAction = new PlayerActionWalk();        
        playerAction.setPlayer(player);
        playerAction.setFromPlace(player.getPlace());
        playerAction.setToPlace(destination);
        
        return playerAction;
    }
    
    public List<PlayerActionWalk> buildTravelingWalking(Player player, List<Place> destinations) 
    {
        List<PlayerActionWalk> ret = new ArrayList<PlayerActionWalk>();
        for (Place destination : destinations) {
            ret.add(buildTravelingWalking(player, destination));
        }
        return ret;
    }
}
