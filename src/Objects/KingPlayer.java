package Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.BajanAmerican.Kings.Kings;

public class KingPlayer {

	private String name;
	private double points;
	private double kills;
	private double deaths;
	private double captures;

	public KingPlayer()
	{

	}

	@SuppressWarnings("deprecation")
	public Player getPlayer()
	{
		return Bukkit.getServer().getPlayerExact(name);
	}   


	public static KingPlayer getKingPlayer(String name)
	{
		return Kings.getInstance().getKingPlayers().get(name);
	}


	public static KingPlayer getKingPlayer(Player player)
	{
		return Kings.getInstance().getKingPlayers().get(player.getName());
	}

	public double getPoints()
	{
		return points;
	}

	public void setPoints(double points)
	{
		this.points = points;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getKills()
	{
		return kills;
	}

	public void setKills(double kills) 
	{
		this.kills = kills;
	}

	public double getDeaths() 
	{
		return deaths;
	}

	public void setDeaths(double deaths) 
	{
		this.deaths = deaths;
	}

	public double getCaptures()
	{
		return captures;
	}

	public void setCaptures(double captures)
	{
		this.captures = captures;
	}

	public void save()
	{
		try
		{
			Kings.getInstance().getMySQL().changePlayerStat(getPlayer(), "points", points);
			Kings.getInstance().getMySQL().changePlayerStat(getPlayer(), "captures", captures);
			Kings.getInstance().getMySQL().changePlayerStat(getPlayer(), "kills", kills);
			Kings.getInstance().getMySQL().changePlayerStat(getPlayer(), "deaths", deaths);
			System.out.println("[Kings] Saved player data for " + name + "!");
		}
		catch(Exception e)
		{
			System.out.println("[Kings] Was NOT able to save player data for " + name + "!");
			e.printStackTrace();
		}
	}
}
