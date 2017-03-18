package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_Pvp implements Listener{

	@EventHandler
	public void onPlayerHurt(EntityDamageByEntityEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(!(event.getEntity() instanceof Player))
			{
				return;
			}
			Player victim = (Player) event.getEntity();
			Player attacker = null;
			if (event.getDamager() instanceof Player)
			{
				attacker = (Player) event.getDamager();
			} 
			else if (event.getDamager() instanceof Arrow) 
			{
				Arrow arrow = (Arrow) event.getDamager();
				if (!(arrow.getShooter() instanceof Player))
				{
					return;
				}
				attacker = (Player) arrow.getShooter();
				arrow.remove();
			} 
			else if (event.getDamager() instanceof Snowball)
			{
				Snowball snowball = (Snowball) event.getDamager();
				if (!(snowball.getShooter() instanceof Player))
				{
					return;
				}
				attacker = (Player) snowball.getShooter();
			}
			if(Kings.getInstance().getGame().isGrace())
				event.setCancelled(true);
			else
			{
				if(Methods.sameTeam(victim, attacker))
					event.setCancelled(true);
			}
		}
	}

}
