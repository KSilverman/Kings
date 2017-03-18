package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.entity.Player;

import Misc.Methods;

public class SQL {

	private Connection connection;

	public SQL()
	{

	}

	private synchronized void openConnection()
	{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password");
		}
		catch(Exception e)
		{
		}
	}

	private synchronized void closeConnection()
	{
		try
		{
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private synchronized boolean playerDataContainsPlayer(Player player)
	{
		try
		{
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `player_data` WHERE player=?;");
			sql.setString(1, player.getUniqueId().toString());
			ResultSet resultSet = sql.executeQuery();
			boolean containsPlayer = resultSet.next();

			sql.close();
			resultSet.close();

			return containsPlayer;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private synchronized boolean playerRankContainsPlayer(Player player)
	{
		try
		{
			PreparedStatement sql = connection.prepareStatement("SELECT * FROM `player_rank` WHERE player=?;");
			sql.setString(1, player.getUniqueId().toString());
			ResultSet resultSet = sql.executeQuery();
			boolean containsPlayer = resultSet.next();

			sql.close();
			resultSet.close();

			return containsPlayer;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public void playerLogin(Player player)
	{
		openConnection();
		try
		{
			int previousKills = 0;

			if(playerDataContainsPlayer(player))
			{
				PreparedStatement sql = connection.prepareStatement("SELECT logins FROM `player_data` WHERE player=?;");
				sql.setString(1, player.getUniqueId().toString());

				ResultSet result = sql.executeQuery();
				result.next();

				previousKills = result.getInt("logins");

				PreparedStatement loginsUpdate = connection.prepareStatement("UPDATE `player_data` SET logins=? WHERE player=?;");
				loginsUpdate.setInt(1, previousKills + 1);
				loginsUpdate.setString(2, player.getUniqueId().toString());
				loginsUpdate.executeUpdate();

				loginsUpdate.close();
				sql.close();
				result.close();
			} 
			else 
			{
				PreparedStatement newPlayer = connection.prepareStatement("INSERT INTO `player_data` values(?,100,0.0,0.0,1,0);");
				newPlayer.setString(1, player.getUniqueId().toString());
				newPlayer.execute();
				newPlayer.close();
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			closeConnection();
		}
	}

	public void playerGiveRanks(Player player)
	{
		openConnection();
		try
		{
			String rank = "";
			if(playerRankContainsPlayer(player))
			{
				PreparedStatement sql = connection.prepareStatement("SELECT rank FROM `player_rank` WHERE player=?;");
				sql.setString(1, player.getUniqueId().toString());

				ResultSet result = sql.executeQuery();
				result.next();

				rank = result.getString("rank");

				if(rank.equalsIgnoreCase("owner"))
				{
					Kings.getInstance().getOwners().add(player.getName());
				}

				if(rank.equalsIgnoreCase("vip"))
				{
					Kings.getInstance().getVips().add(player.getName());
				}

				if(rank.equalsIgnoreCase("mod"))
				{
					Kings.getInstance().getMods().add(player.getName());
				}

				if(rank.equalsIgnoreCase("master"))
				{
					Kings.getInstance().getMasters().add(player.getName());
				}

				if(rank.equalsIgnoreCase("admin"))
				{
					Kings.getInstance().getAdmins().add(player.getName());
				}

				if(rank.equalsIgnoreCase("legend"))
				{
					Kings.getInstance().getLegends().add(player.getName());
				}
				if(rank.equalsIgnoreCase("builder"))
				{
					Kings.getInstance().getBuilders().add(player.getName());
				}

				if(rank.equalsIgnoreCase("coder"))
				{
					Kings.getInstance().getCoders().add(player.getName());
				}

				if(rank.equalsIgnoreCase("god"))
				{
					Kings.getInstance().getGods().add(player.getName());
				}

				sql.close();
				result.close();
			} 
			else 
			{
				if(!(Methods.isRanked(player)))
					return;
				PreparedStatement newPlayer = connection.prepareStatement("INSERT INTO `player_rank` values(?,?);");
				newPlayer.setString(1, player.getUniqueId().toString());
				if(Kings.getInstance().getOwners().contains(player.getName()))
				{
					newPlayer.setString(2, "owner");
				} 
				else if(Kings.getInstance().getVips().contains(player.getName()))
				{
					newPlayer.setString(2, "vip");
				} 
				else if(Kings.getInstance().getMods().contains(player.getName()))
				{
					newPlayer.setString(2, "mod");
				} 
				else if(Kings.getInstance().getMasters().contains(player.getName()))
				{
					newPlayer.setString(2, "master");
				} 
				else if(Kings.getInstance().getAdmins().contains(player.getName()))
				{
					newPlayer.setString(2, "admin");
				} 
				else if(Kings.getInstance().getLegends().contains(player.getName()))
				{
					newPlayer.setString(2, "legend");
				}  
				else if(Kings.getInstance().getBuilders().contains(player.getName()))
				{
					newPlayer.setString(2, "builder");
				} 
				else if(Kings.getInstance().getCoders().contains(player.getName()))
				{
					newPlayer.setString(2, "coder");
				} 
				else if(Kings.getInstance().getGods().contains(player.getName()))
				{
					newPlayer.setString(2, "god");
				}
				newPlayer.execute();
				newPlayer.close();
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			closeConnection();
		}
	}

	public double getPlayerStat(Player player, String destination)
	{
		double temp = 0;
		openConnection();
		try
		{
			if(playerDataContainsPlayer(player))
			{
				PreparedStatement sql = connection.prepareStatement("SELECT " + destination + " FROM `player_data` WHERE player=?;");
				sql.setString(1, player.getUniqueId().toString());

				ResultSet result = sql.executeQuery();
				result.next();

				temp = result.getDouble(destination);

				sql.close();
				result.close();
			} 
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			closeConnection();
		}
		return temp;
	}

	public void changePlayerStat(Player player, String destination, double amount)
	{
		openConnection();
		try
		{ 		
			if(playerDataContainsPlayer(player))
			{
				PreparedStatement sql = connection.prepareStatement("SELECT " + destination + " FROM `player_data` WHERE player=?;");
				sql.setString(1, player.getUniqueId().toString());

				ResultSet result = sql.executeQuery();
				result.next();


				PreparedStatement loginsUpdate = connection.prepareStatement("UPDATE `player_data` SET " + destination + "=? WHERE player=?;");
				loginsUpdate.setDouble(1, amount);
				loginsUpdate.setString(2, player.getUniqueId().toString());
				loginsUpdate.executeUpdate();

				loginsUpdate.close();
				sql.close();
				result.close();
			} 
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			closeConnection();
		}
	}

	public Connection getConnection()
	{
		return connection;
	}

}

