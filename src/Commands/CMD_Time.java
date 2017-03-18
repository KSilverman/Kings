package Commands;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Commands.SimpleCommand.CommandHandler;
import Misc.Methods;
import Objects.GameState;
import Objects.LobbyCountdown;

public class CMD_Time {

	@CommandHandler(name = "timerset")
	public void onCommand(CommandSender sender, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(Methods.modUp(player))
			{
				if(args.length == 1)
				{
					try
					{
						Integer.valueOf(args[0]);
					}
					catch(NumberFormatException nfe)
					{
						player.sendMessage(Kings.getInstance().getStarter() + "§cInproper usage! Please enter a valid integer");
						return;
					}
					if(Kings.getInstance().getGameState().getState() == GameState.IN_LOBBY)
					{
						if(Integer.valueOf(args[0]).intValue() <= 120 && Integer.valueOf(args[0]).intValue() > 0)
						{
							LobbyCountdown lcd = Kings.getInstance().getLobbyCountdown();
							lcd.setTime(Integer.valueOf(args[0]).intValue());
							Bukkit.broadcastMessage(ChatColor.AQUA + "[Lobby] " + ChatColor.WHITE + "" + ChatColor.BOLD + sender.getName() + ChatColor.DARK_GREEN + " has changed the countdown timer to " + ChatColor.RED + "" + ChatColor.BOLD + String.valueOf(Integer.valueOf(args[0]).intValue()) + ChatColor.DARK_GREEN + " seconds!");
						}
						else
							player.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "Please enter a valid integer from 1-120.");
					}
					else
						player.sendMessage(Kings.getInstance().getStarter() + ChatColor.DARK_RED + "You are not able to use this command in this gamestate!" + ChatColor.GREEN + " Current Gamestate: " + ChatColor.BLUE + Kings.getInstance().getGameState().getState());
				}
				else
				{
					player.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "Inproper usage! /timerset <int>");
					return;
				}
			}
			else
			{
				player.sendMessage(Kings.getInstance().getStarter() + Kings.getInstance().getPermMsg());
				return;
			}
		}
		else
		{
			if(args.length == 1)
			{
				try
				{
					Integer.valueOf(args[0]);
				}
				catch(NumberFormatException nfe)
				{
					sender.sendMessage(Kings.getInstance().getStarter() + "§cInproper usage! Please enter a valid integer");
					return;
				}
				if(Kings.getInstance().getGameState().getState() == GameState.IN_LOBBY)
				{
					if(Integer.valueOf(args[0]).intValue() <= 120 && Integer.valueOf(args[0]).intValue() > 0)
					{
						LobbyCountdown lcd = Kings.getInstance().getLobbyCountdown();
						lcd.setTime(Integer.valueOf(args[0]).intValue());
						Bukkit.broadcastMessage(ChatColor.AQUA + "[Lobby] " + ChatColor.WHITE + "" + ChatColor.BOLD + sender.getName() + ChatColor.DARK_GREEN + " has changed the countdown timer to " + ChatColor.RED + "" + ChatColor.BOLD + String.valueOf(Integer.valueOf(args[0]).intValue()) + ChatColor.DARK_GREEN + " seconds!");
					}
					else
						sender.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "Please enter a valid integer from 1-120.");
				}
				else
					sender.sendMessage(Kings.getInstance().getStarter() + ChatColor.DARK_RED + "You are not able to use this command in this gamestate!" + ChatColor.GREEN + " Current Gamestate: " + ChatColor.BLUE + Kings.getInstance().getGameState().getState());
			}
		}
	}

}
