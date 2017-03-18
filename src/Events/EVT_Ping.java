package Events;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import Objects.GameState;

public class EVT_Ping implements Listener{

	@EventHandler
	public void onServerListPing(ServerListPingEvent event)
	{
		if(Kings.getInstance().getGameState().getState().equalsIgnoreCase(GameState.IN_LOBBY))
		{
			event.setMotd("&a&lJOIN NOW");
		} 
		else if(Kings.getInstance().getGameState().getState().equalsIgnoreCase(GameState.IN_GAME))
		{
			event.setMotd("&6&lIN PROGRESS");
		} 
		else
		{
			event.setMotd("&c&lRESTARTING");
		}
	}

}
