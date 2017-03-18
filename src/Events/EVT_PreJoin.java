package Events;


import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_PreJoin implements Listener{

	@EventHandler
	public void playerPreJoin(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();

		if((Kings.getInstance().getGameState().getState() == GameState.IN_LOBBY) && (Bukkit.getOnlinePlayers().length == 48))
		{
			if(Methods.isRanked(player))
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(!(Methods.isRanked(p)))
					{
						Methods.sendToHub(p);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "A(N) " + Methods.getRankName(player.getName()) + ChatColor.GREEN + " HAS SIGNED IN!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to purchase a rank!");
					}
					else if(Methods.legendUp(player) && !Methods.legendUp(p))
					{
						Methods.sendToHub(p);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "A(N) " + Methods.getRankName(player.getName()) + ChatColor.GREEN + " HAS SIGNED IN!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to purchase a higher rank!");
					}
					else if(Methods.godUp(player) && !Methods.godUp(p))
					{
						Methods.sendToHub(p);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "A(N) " + Methods.getRankName(player.getName()) + ChatColor.GREEN + " HAS SIGNED IN!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to purchase a higher rank!");
					}
					else if(Methods.vipUp(player) && !Methods.vipUp(p))
					{
						Methods.sendToHub(p);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "A(N) " + Methods.getRankName(player.getName()) + ChatColor.GREEN + " HAS SIGNED IN!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to apply for a higher rank!");
					}
					else if(Methods.coderUp(player) && !Methods.modUp(p))
					{
						Methods.sendToHub(p);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "A(N) " + Methods.getRankName(player.getName()) + ChatColor.GREEN + " HAS SIGNED IN!\n" + ChatColor.AQUA + "[TheParkMC] " + ChatColor.RED + "We are sorry for the inconvinience!");
					}
					else
					{
						Methods.sendToHub(player);
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.DARK_RED + "This server is full!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to purchase or apply for a higher rank!");
					}
				}
			}
			else
			{
				Methods.sendToHub(player);
				player.sendMessage(Kings.getInstance().getStarter() + ChatColor.DARK_RED + "This server is full!\n" + ChatColor.LIGHT_PURPLE + "Go to " + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "http://www.theparkmc.com" + ChatColor.LIGHT_PURPLE + " to purchase a rank!");
			}
		}
	}

}
