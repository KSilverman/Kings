package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import Misc.Methods;
import Objects.GameState;
import Objects.Team;

public class EVT_Interact implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			try
			{
				if(event.getPlayer().getItemInHand().getType() == Material.ARROW)
				{
					if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
						Methods.openArrow(event.getPlayer());
				}
			}
			catch(Exception e)
			{

			}
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			try
			{
				if(event.getPlayer().getItemInHand().getType() == Material.MAP)
				{
					if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						Inventory inv = Bukkit.createInventory(event.getPlayer(), Methods.getSizeForMap(), ChatColor.BOLD + "Spectator Teleporter");
						for(Team team : Kings.getInstance().getTeams())
						{
							for(Player pp : team.getRealPlayers())
							{
								@SuppressWarnings("deprecation")
								ItemStack skull = new ItemStack(397, 1, (short) 3);
								SkullMeta meta = (SkullMeta) skull.getItemMeta();
								if(Methods.isKing(pp))
									meta.setDisplayName(team.getDarkerShade(team.getChatColor()) + pp.getName());
								else
									meta.setDisplayName(team.getChatColor() + pp.getName());
								skull.setItemMeta(meta);
								inv.addItem(skull);
							}
						}
						event.getPlayer().openInventory(inv);
					}
				}
			}
			catch(Exception e)
			{

			}
		}

		try
		{
			if(event.getPlayer().getItemInHand().getType() == Material.COMPASS)
			{
				if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				{
					Methods.sendToHub(event.getPlayer());
					event.getPlayer().sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "Teleported to hub!");
				}
			}
		}
		catch(Exception e)
		{

		}
	}

}
