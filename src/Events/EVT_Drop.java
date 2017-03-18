package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import Objects.GameState;

public class EVT_Drop implements Listener{

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			event.setCancelled(true);

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getSpectators().contains(event.getPlayer().getName()))
				event.setCancelled(true);
		}
	}

}
