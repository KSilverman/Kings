package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_InvClick implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInvClick(InventoryClickEvent event)
	{
		if(!(event.getWhoClicked() instanceof Player))
		{
			return;
		}

		Player player = (Player) event.getWhoClicked();

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			event.setCancelled(true);
			if(event.getInventory().getName().equals(ChatColor.RED + "Kit Selection"))
			{
				Kings.getInstance().getClasses().removeFromClass(player.getName());
				if(event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE)
				{
					Kings.getInstance().getClasses().getJugs().add(player.getName());
					player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "You have chosen the " + ChatColor.GOLD + "JUGGERNAUT " + ChatColor.GREEN + "class!");
				}
				else if(event.getCurrentItem().getType() == Material.STONE_PICKAXE)
				{
					Kings.getInstance().getClasses().getJugglers().add(player.getName());
					player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "You have chosen the " + ChatColor.YELLOW + "JUGGLER " + ChatColor.GREEN + "class!");
				}
				else if(event.getCurrentItem().getType() == Material.DIAMOND_SWORD)
				{
					Kings.getInstance().getClasses().getKnights().add(player.getName());
					player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "You have chosen the " + ChatColor.GRAY + "KNIGHT " + ChatColor.GREEN + "class!");
				}
				else if(event.getCurrentItem().getType() == Material.DIAMOND_BOOTS)
				{
					Kings.getInstance().getClasses().getFlashes().add(player.getName());
					player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "You have chosen the " + ChatColor.RED + "FLASH " + ChatColor.GREEN + "class!");
				}
				player.closeInventory();
			}
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getSpectators().contains(player.getName()))
				event.setCancelled(true);
			if(event.getInventory().getName().equals(ChatColor.BOLD + "Spectator Teleporter"))
			{
				if(event.getCurrentItem() == null)
				{
					event.setCancelled(true);
					return;
				}

				if(event.getCurrentItem().getItemMeta().getDisplayName() == null)
					return;

				Player px = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
				if(px != null)
				{
					if(Methods.isInTeam(px))
					{
						player.teleport(px);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "Teleported you to " + Methods.getTeam(px).getChatColor() + event.getCurrentItem().getItemMeta().getDisplayName());
						player.closeInventory();
					}
				}
				else
					player.closeInventory();
			}
		}
	}

}
