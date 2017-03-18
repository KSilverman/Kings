package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import Misc.Methods;
import Objects.GameState;
import Objects.KingPlayer;

public class EVT_Join implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.GAME_RESETING))
		{
			Methods.sendToHub(event.getPlayer());
			event.getPlayer().sendMessage(ChatColor.AQUA + "[TheParkMC] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "You CANNOT Join During Cleanup!");
		}

		if(!(Kings.getInstance().getKingPlayers().containsKey(event.getPlayer().getName())))
		{
			KingPlayer Kingsp = new KingPlayer();
			Kingsp.setName(event.getPlayer().getName());
			Kingsp.setPoints(Kings.getInstance().getMySQL().getPlayerStat(event.getPlayer(), "points"));
			Kingsp.setCaptures(Kings.getInstance().getMySQL().getPlayerStat(event.getPlayer(), "captures"));
			Kingsp.setDeaths(Kings.getInstance().getMySQL().getPlayerStat(event.getPlayer(), "deaths"));
			Kingsp.setKills(Kings.getInstance().getMySQL().getPlayerStat(event.getPlayer(), "kills"));
			Kings.getInstance().getKingPlayers().put(event.getPlayer().getName(), Kingsp);
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			event.setJoinMessage(null);
			Kings.getInstance().getMySQL().playerGiveRanks(event.getPlayer());
			Kings.getInstance().getMySQL().playerLogin(event.getPlayer());
			Methods.doSpectatorDance(event.getPlayer());
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			Kings.getInstance().getMySQL().playerGiveRanks(event.getPlayer());
			Kings.getInstance().getMySQL().playerLogin(event.getPlayer());

			event.getPlayer().sendMessage(ChatColor.AQUA + "Welcome " + ChatColor.RESET + ChatColor.UNDERLINE + event.getPlayer().getName() + ChatColor.AQUA + " To" + ChatColor.LIGHT_PURPLE + " Kings!\n\n");

			event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "\nKings!\n" + ChatColor.BLACK + "-----------------\n" + ChatColor.GOLD + "Owned By:" + ChatColor.RESET + ChatColor.ITALIC + ChatColor.BOLD + " xhockey + BajanAmerican\n" + ChatColor.DARK_GREEN + "Developed By:" + ChatColor.WHITE + ChatColor.BOLD + " BajanAmerican\n");

			event.getPlayer().sendMessage(ChatColor.AQUA+ "\nThere Are " + ChatColor.DARK_AQUA + String.valueOf(Kings.getInstance().getLobbyCountdown().getTime()) + ChatColor.AQUA + " Seconds Until The Game Starts!\n");

			event.getPlayer().setGameMode(GameMode.SURVIVAL);
			event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
			event.getPlayer().setHealth(20);
			event.getPlayer().setFoodLevel(20);
			event.getPlayer().setLevel(Kings.getInstance().getLobbyCountdown().getTime());
			event.getPlayer().getInventory().clear();
			event.getPlayer().getInventory().setArmorContents(
					new ItemStack[] { new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR) });
			Kings.getInstance().getClasses().giveLobbyGoodies();

			if(Methods.isRanked(event.getPlayer()))
			{
				Methods.setListName(event.getPlayer(), Methods.getRankColor(event.getPlayer()) + event.getPlayer().getName());
				event.setJoinMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ">> " + Methods.getRankColor(event.getPlayer()) + event.getPlayer().getName() + ChatColor.AQUA + " has joined Kings!");
			}
			else
			{
				Methods.setListName(event.getPlayer(), ChatColor.GREEN + event.getPlayer().getName());
				event.setJoinMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ">> " + ChatColor.GREEN + event.getPlayer().getName() + ChatColor.AQUA + " has joined Kings!");
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Kings.getInstance().getClasses().removeFromClass(event.getPlayer().getName());
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			if(Methods.modUp(event.getPlayer()))
				event.setQuitMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + ">> " + Methods.getRankColor(event.getPlayer()) + event.getPlayer().getName() + ChatColor.RED + " has left Kings!");
			else
				event.setQuitMessage(null);
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getSpectators().contains(event.getPlayer().getName()))
				event.setQuitMessage(null);
			else
			{
				if(Methods.isInTeam(event.getPlayer()))
				{
					KingPlayer kp = KingPlayer.getKingPlayer(event.getPlayer());
					kp.setPoints(kp.getPoints() - 3);
					kp.setDeaths(kp.getDeaths() + 1);
					if(Methods.isKing(event.getPlayer()))
						event.setQuitMessage(Methods.getTeam(event.getPlayer()).getDarkerShade(Methods.getTeam(event.getPlayer()).getChatColor()) + event.getPlayer().getName() + ChatColor.YELLOW + " died for leaving the game");
					else
						event.setQuitMessage(Methods.getTeam(event.getPlayer()).getChatColor() + event.getPlayer().getName() + ChatColor.YELLOW + " died for leaving the game");
					Methods.getTeam(event.getPlayer()).getPlayers().remove(event.getPlayer().getName());
				}
			}
		}
	}

}
