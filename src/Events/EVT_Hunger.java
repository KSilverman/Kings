package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import Objects.GameState;

public class EVT_Hunger implements Listener{

	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			event.setCancelled(true);
	}

}
