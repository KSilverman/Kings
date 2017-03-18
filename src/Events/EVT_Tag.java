package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

import Misc.Methods;
import Objects.GameState;

public class EVT_Tag implements Listener{

	@EventHandler
	public void onPlayerTagReciece(AsyncPlayerReceiveNameTagEvent event) 
	{
		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_GAME))
		{
			if(Kings.getInstance().getEdward().getPlayers().contains(event.getNamedPlayer().getName()))
			{
				if(!(Methods.isKing(event.getNamedPlayer())))
					event.setTag(Kings.getInstance().getEdward().getChatColor() + event.getNamedPlayer().getName());
				else
					event.setTag(Kings.getInstance().getEdward().getKingName());
			}
			else if(Kings.getInstance().getHenry().getPlayers().contains(event.getNamedPlayer().getName()))
			{
				if(!(Methods.isKing(event.getNamedPlayer())))
					event.setTag(Kings.getInstance().getHenry().getChatColor() + event.getNamedPlayer().getName());
				else
					event.setTag(Kings.getInstance().getHenry().getKingName());
			}
			else if(Kings.getInstance().getCharles().getPlayers().contains(event.getNamedPlayer().getName()))
			{
				if(!(Methods.isKing(event.getNamedPlayer())))
					event.setTag(Kings.getInstance().getCharles().getChatColor() + event.getNamedPlayer().getName());
				else
					event.setTag(Kings.getInstance().getCharles().getKingName());
			}
			else if(Kings.getInstance().getJames().getPlayers().contains(event.getNamedPlayer().getName()))
			{
				if(!(Methods.isKing(event.getNamedPlayer())))
					event.setTag(Kings.getInstance().getJames().getChatColor() + event.getNamedPlayer().getName());
				else
					event.setTag(Kings.getInstance().getJames().getKingName());
			}
		}

		if(Kings.getInstance().getGameState().getState().equals(GameState.IN_LOBBY))
		{
			if(Kings.getInstance().getOwners().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.DARK_RED + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getAdmins().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.RED + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getCoders().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.RED + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getMods().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.DARK_AQUA + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getVips().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.DARK_PURPLE + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getBuilders().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.LIGHT_PURPLE + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getGods().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.AQUA + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getLegends().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.GOLD + event.getNamedPlayer().getName());
			else if(Kings.getInstance().getMasters().contains(event.getNamedPlayer().getName()))
				event.setTag(ChatColor.GRAY + event.getNamedPlayer().getName());
			else
				event.setTag(ChatColor.GREEN + event.getNamedPlayer().getName());
		}
	}

}
