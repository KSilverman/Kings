package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_Move implements Listener{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			if(event.getPlayer().getLocation().getY() < 5)
				event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
		}
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getGame().isGrace() && Methods.isMiddle(event.getTo()))
			{
				event.setTo(event.getFrom());
			}
		}
	}

}
