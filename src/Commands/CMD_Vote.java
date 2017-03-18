package Commands;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Commands.SimpleCommand.CommandHandler;
import Misc.Methods;
import Objects.GameState;

public class CMD_Vote 
{
	@SuppressWarnings("deprecation")
	@CommandHandler(name = "vote")
    public void onCommand(CommandSender sender, String[] args)
    {
		  if(sender instanceof Player)
		  {
			  Player player = (Player) sender;
			  if(args.length == 1)
			  {
				  if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
				  {
					  if(Kings.getInstance().getGraceCountdown().getTime() > 240)
					  {
						  if(!(Kings.getInstance().getVoted().contains(player.getName())))
						  {
							  if(Bukkit.getPlayer(args[0]).isOnline())
							  {
								  if(Methods.getTeam(player).equals(Methods.getTeam(Bukkit.getPlayer(args[0]))))
								  {
									  Kings.getInstance().getKingVotes().put(Bukkit.getPlayer(args[0]), Kings.getInstance().getGame().getVotes(Bukkit.getPlayer(args[0])) + 1);
									  player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "Successfully voted for " + Methods.getTeam(player).getChatColor() + args[0] + ChatColor.GREEN + "!");
								  }
								  else
								  {
									  player.sendMessage(Kings.getInstance().getStarter() + Methods.getTeam(Bukkit.getPlayer(args[0])).getChatColor() + args[0] + ChatColor.RED + " is not on your team!");
									  return;
								  }
							  }
							  else
							  {
								  player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GRAY + args[0] + ChatColor.RED + " is not online!");
								  return;
							  }
						  }
						  else
						  {
							  player.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "You already voted for a King silly!");
							  return;
						  }
					  }
					  else
					  {
						  player.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "You can only vote for a King in the first minute of the game!");
						  return;
					  }
				  }
				  else
				  {
					  player.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "You must be in game to use this command!");
					  return;
				  }
			  }
			  else
			  {
				  player.sendMessage(Kings.getInstance().getStarter() + "Improper usage! /vote <name>");
				  return;
			  }
		  }
		  else
		  {
			  sender.sendMessage("You must be a player to execute this command!");
			  return;
		  }
    }
}
