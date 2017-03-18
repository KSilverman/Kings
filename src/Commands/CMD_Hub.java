package Commands;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Commands.SimpleCommand.CommandHandler;
import Misc.Methods;
import Objects.GameState;
import Objects.KingPlayer;

public class CMD_Hub {
	
	@CommandHandler(name = "hub")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			Methods.sendToHub(player);
			player.sendMessage(Kings.getInstance().getStarter() + ChatColor.GREEN + "Teleported to hub!");
			Kings.getInstance().getClasses().removeFromClass(player.getName());
			if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
			{
				if(Methods.modUp(player))
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + ">> " + Methods.getRankColor(player) + player.getName() + ChatColor.RED + " has left Kings!");
			}
			
			if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
			{
					if(Methods.isInTeam(player))
					{
						KingPlayer kp = KingPlayer.getKingPlayer(player);
						kp.setPoints(kp.getPoints() - 3);
						kp.setDeaths(kp.getDeaths() + 1);
						if(Methods.isKing(player))
							Bukkit.broadcastMessage(Methods.getTeam(player).getDarkerShade(Methods.getTeam(player).getChatColor()) + player.getName() + ChatColor.YELLOW + " died for leaving the game");
						else
							Bukkit.broadcastMessage(Methods.getTeam(player).getChatColor() + player.getName() + ChatColor.YELLOW + " died for leaving the game");
						Methods.getTeam(player).getPlayers().remove(player.getName());
					}
				}
			}
			else
			{
				sender.sendMessage(Kings.getInstance().getStarter() + "You must be a player to use this command!");
				return;
			}
    	}

}
