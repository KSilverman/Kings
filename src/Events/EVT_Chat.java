package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import Misc.Methods;
import Objects.GameState;
import Objects.KingPlayer;
import Objects.Team;

public class EVT_Chat implements Listener{

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		KingPlayer kp = KingPlayer.getKingPlayer(player);
		String origonal = ChatColor.RESET + event.getMessage();
		String points = ChatColor.GRAY + "(" + ChatColor.DARK_PURPLE + String.valueOf(kp.getPoints()) + ChatColor.GRAY + ")";
		String dead = ChatColor.DARK_RED + "[DEAD]";
		String rankName = ChatColor.GRAY + "(" + Methods.getRankName(player.getName()) + ChatColor.GRAY + ")";
		String colon = ChatColor.GRAY + ": ";

		String ownerName = ChatColor.DARK_RED + player.getName();
		String adminName = ChatColor.RED + player.getName();
		String modName = ChatColor.DARK_AQUA + player.getName();
		String vipName = ChatColor.DARK_PURPLE + player.getName();
		String builderName = ChatColor.LIGHT_PURPLE + player.getName();
		String godName = ChatColor.AQUA + player.getName();
		String legName = ChatColor.GOLD + player.getName();
		String masterName = ChatColor.GRAY + player.getName();
		String defName = ChatColor.GREEN + player.getName();

		if(Kings.getInstance().getGameState().getState().equalsIgnoreCase(GameState.IN_LOBBY))
		{
			if(Kings.getInstance().getOwners().contains(player.getName()))
				event.setFormat(points + rankName + ownerName + colon + origonal);
			else if(Kings.getInstance().getAdmins().contains(player.getName()))
				event.setFormat(points + rankName + adminName + colon + origonal);
			else if(Kings.getInstance().getCoders().contains(player.getName()))
				event.setFormat(points + rankName + adminName + colon + origonal);
			else if(Kings.getInstance().getMods().contains(player.getName()))
				event.setFormat(points + rankName + modName + colon + origonal);
			else if(Kings.getInstance().getVips().contains(player.getName()))
				event.setFormat(points + rankName + vipName + colon + origonal);
			else if(Kings.getInstance().getBuilders().contains(player.getName()))
				event.setFormat(points + rankName + builderName + colon + origonal);
			else if(Kings.getInstance().getGods().contains(player.getName()))
				event.setFormat(points + rankName + godName + colon + origonal);
			else if(Kings.getInstance().getLegends().contains(player.getName()))
				event.setFormat(points + rankName + legName + colon + origonal);
			else if(Kings.getInstance().getMasters().contains(player.getName()))
				event.setFormat(points + rankName + masterName + colon + origonal);
			else
				event.setFormat(points + defName + colon + origonal);
		}

		if(Kings.getInstance().getGameState().getState().equalsIgnoreCase(GameState.IN_GAME))
		{
			if(Kings.getInstance().getSpectators().contains(player.getName()))
			{
				if(Kings.getInstance().getOwners().contains(player.getName()))
					event.setFormat(dead + points + rankName + ownerName + colon + origonal);
				else if(Kings.getInstance().getAdmins().contains(player.getName()))
					event.setFormat(dead +points + rankName + adminName + colon + origonal);
				else if(Kings.getInstance().getCoders().contains(player.getName()))
					event.setFormat(dead +points + rankName + adminName + colon + origonal);
				else if(Kings.getInstance().getMods().contains(player.getName()))
					event.setFormat(dead +points + rankName + modName + colon + origonal);
				else if(Kings.getInstance().getVips().contains(player.getName()))
					event.setFormat(dead +points + rankName + vipName + colon + origonal);
				else if(Kings.getInstance().getBuilders().contains(player.getName()))
					event.setFormat(dead +points + rankName + builderName + colon + origonal);
				else if(Kings.getInstance().getGods().contains(player.getName()))
					event.setFormat(dead +points + rankName + godName + colon + origonal);
				else if(Kings.getInstance().getLegends().contains(player.getName()))
					event.setFormat(dead +points + rankName + legName + colon + origonal);
				else if(Kings.getInstance().getMasters().contains(player.getName()))
					event.setFormat(dead +points + rankName + masterName + colon + origonal);
				else
					event.setFormat(dead +points + defName + colon + origonal);
				for(Team team : Kings.getInstance().getTeams())
				{
					for(Player p : team.getRealPlayers())
						event.getRecipients().remove(p);
				}
			}
			else
			{
				if(Methods.isKing(player))
				{
					if(Methods.isRanked(player))
						event.setFormat(rankName + Methods.getTeam(player).getDarkerShade(Methods.getTeam(player).getChatColor()) + player.getName() + colon + origonal);
					else
						event.setFormat(Methods.getTeam(player).getDarkerShade(Methods.getTeam(player).getChatColor()) + player.getName() + colon + origonal);
				}
				else
				{
					if(Methods.isRanked(player))
						event.setFormat(rankName + Methods.getTeam(player).getChatColor() + player.getName() + colon + origonal);
					else
						event.setFormat(Methods.getTeam(player).getChatColor() + player.getName() + colon + origonal);
				}
				for(Team team : Kings.getInstance().getTeams())
				{
					if(!(team.equals(Methods.getTeam(player))))
					{
						for(Player p : team.getRealPlayers())
							event.getRecipients().remove(p);
					}
				}
			}
		}
	}

}
