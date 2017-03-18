package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import Objects.GameState;

public class EVT_Damage implements Listener{

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{		
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getGame().isGrace())
			{
				if(event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK || event.getCause() == DamageCause.BLOCK_EXPLOSION)
					event.setCancelled(true);
			}
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			event.setCancelled(true);
	}

}
