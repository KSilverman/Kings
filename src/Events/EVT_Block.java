package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_Block implements Listener{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			event.setCancelled(true);
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getGame().isGrace())
			{
				if(Methods.isMiddle(event.getBlockPlaced().getLocation()))
					event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			event.setCancelled(true);
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getGame().isGrace())
			{
				if(Methods.isMiddle(event.getBlock().getLocation()))
					event.setCancelled(true);
			}
		}
	}

}
