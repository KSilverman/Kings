package Objects;

import org.bukkit.ChatColor;

public class GameState 
{
	public static String IN_LOBBY = "§A&lJOIN NOW";
	public static final String IN_GAME = "§C&lIN PROGRESS";
	public static final String GAME_RESETING = ChatColor.DARK_GREEN + "§4&lRESETTING";

	private String gameState;

	public void setGameState(String state) 
	{
		this.gameState = state;
	}

	public String getState()
	{
		return this.gameState;
	}

	public void setInLobby() 
	{
		setGameState(IN_LOBBY);
	}

	public void setInGame()
	{
		setGameState(IN_GAME);
	}
}
