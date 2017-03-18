package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import Objects.GameState;

public class EVT_Health implements Listener{

	@EventHandler
	public void onPlayerHeathRegen(EntityRegainHealthEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
			event.setCancelled(true);
	}

}
