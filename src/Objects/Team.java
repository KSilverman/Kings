package Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team 
{
	private String name;
	private ChatColor color;
	private List<String> players;

	private Player King;

	public Team(String name, ChatColor color)
	{
		this.name = name;
		this.color = color;
		players = new ArrayList<String>();
	}

	public String toString()
	{
		return color + "[" + color + name + color + "]";
	}

	public List<String> getPlayers()
	{
		return players;
	}

	@SuppressWarnings("deprecation")
	public Player[] getRealPlayers()
	{
		Player[] result = new Player[players.size()];
		for(int i = 0; i < players.size(); i++)
			result[i] = Bukkit.getPlayer(players.get(i));
		return result;
	}

	public ChatColor getChatColor()
	{
		return color;
	}

	public String getName()
	{
		return name;
	}

	public void setKing(Player king)
	{
		this.King = king;
	}

	public Player getKing()
	{
		return King;
	}

	public String getKingName()
	{
		return (!(King == null)) ? getDarkerShade(color) + King.getName() : getDarkerShade(color) + "NONE";
	}

	public ChatColor getDarkerShade(ChatColor color)
	{
		if(color.equals(ChatColor.YELLOW))
			return ChatColor.GOLD;
		else if(color.equals(ChatColor.RED))
			return ChatColor.DARK_RED;
		else if(color.equals(ChatColor.BLUE))
			return ChatColor.DARK_BLUE;
		else if(color.equals(ChatColor.GREEN))
			return ChatColor.DARK_GREEN;
		else if(color.equals(ChatColor.LIGHT_PURPLE))
			return ChatColor.DARK_PURPLE;
		else if(color.equals(ChatColor.GRAY))
			return ChatColor.DARK_GRAY;
		else if(color.equals(ChatColor.AQUA))
			return ChatColor.DARK_AQUA;
		return color;
	}
}

