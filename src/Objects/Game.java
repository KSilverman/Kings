package Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Misc.Methods;

public class Game {

	int edwardkills;
	int henrykills;
	int charleskills;
	int jameskills;

	public Game()
	{
		edwardkills = 0;
		henrykills = 0;
		charleskills = 0;
		jameskills = 0;
	}

	public void start()
	{
		Kings.getInstance().getGameState().setInGame();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.getInventory().clear();
			p.getActivePotionEffects().clear();
			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.getWorld().setTime(0);
			if(!(Kings.getInstance().getPlayerKillStreaks().containsKey(p.getName())))
				Kings.getInstance().getPlayerKillStreaks().put(p.getName(), 0);
		}
		splitTeams();
		Kings.getInstance().getClasses().giveDefaultClasses();
		Kings.getInstance().getClasses().giveDefaultClass();
		Kings.getInstance().getClasses().giveSpecialEffects();
	}

	public void end()
	{
		Kings.getInstance().getGameState().setGameState(GameState.GAME_RESETING);
		Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§6§lServer Restarting...");
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(p.getWorld().getSpawnLocation());
			p.setAllowFlight(false);
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.getInventory().setArmorContents(
					new ItemStack[] { new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR) });
			p.closeInventory();
			Methods.sendToHub(p);
		}

		for(KingPlayer cp : Kings.getInstance().getKingPlayers().values())
			cp.save();

		Methods.clearAllLists();
		Kings.getInstance().getServer().shutdown();
	}

	private void splitTeams()
	{
		int index = 1;
		for(Player p : Bukkit.getOnlinePlayers())
		{
			switch(index)
			{
			case 1:
			{
				Kings.getInstance().getEdward().getPlayers().add(p.getName());
				break;
			}
			case 2:
			{
				Kings.getInstance().getHenry().getPlayers().add(p.getName());
				break;
			}
			case 3:
			{
				Kings.getInstance().getCharles().getPlayers().add(p.getName());
				break;
			}
			case 4:
			{
				Kings.getInstance().getJames().getPlayers().add(p.getName());
			}
			}
			index++;
			if(index == 5)
				index = 1;
		}

		for(Player p : Kings.getInstance().getEdward().getRealPlayers())
		{
			p.sendMessage(Kings.getInstance().getStarter() + ChatColor.YELLOW + "You are on team " + "§l" + Methods.getTeam(p).toString() + ChatColor.YELLOW + "!");
			p.teleport(new Location(Bukkit.getWorld("Kings"), -374, 30, 187));
		}

		for(Player p : Kings.getInstance().getHenry().getRealPlayers())
		{
			p.sendMessage(Kings.getInstance().getStarter() + ChatColor.YELLOW + "You are on team " + "§l" + Methods.getTeam(p).toString() + ChatColor.YELLOW + "!");
			p.teleport(new Location(Bukkit.getWorld("Kings"), -295, 30, 349));
		}
		for(Player p : Kings.getInstance().getCharles().getRealPlayers())
		{
			p.sendMessage(Kings.getInstance().getStarter() + ChatColor.YELLOW + "You are on team " +"§l" +  Methods.getTeam(p).toString() + ChatColor.YELLOW + "!");
			p.teleport(new Location(Bukkit.getWorld("Kings"), -452, 30, 426));
		}

		for(Player p : Kings.getInstance().getJames().getRealPlayers())
		{
			p.sendMessage(Kings.getInstance().getStarter() + ChatColor.YELLOW + "You are on team " + "§l" + Methods.getTeam(p).toString() + ChatColor.YELLOW + "!");
			p.teleport(new Location(Bukkit.getWorld("Kings"), -533, 30, 269));
		}

		removeExcessPlayers();
	}

	private void removeExcessPlayers()
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			for(Player p : team.getRealPlayers())
			{
				if(!(p.isOnline()))
					team.getPlayers().remove(p.getName());
			}
		}
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(Methods.getTeam(p) == null)
			{
				Methods.sendToHub(p);
				p.sendMessage(Kings.getInstance().getStarter() + ChatColor.RED + "TEAM UNDEFINED! Please report this bug to the developer! @Bajan_American");
			}
		}
	}

	public boolean isGrace()
	{
		return Kings.getInstance().getGraceCountdown().getTime() > 0;
	}

	public void findKings()
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			Integer[] votes = new Integer[team.getRealPlayers().length];
			for(int i = 0; i < team.getRealPlayers().length; i++)
				votes[i] = getVotes(team.getRealPlayers()[i]);
			for(Player p : team.getRealPlayers())
			{
				if(findLargest(votes) == getVotes(p))
				{
					if(highestAreEqual(votes) == false)
						team.setKing(p);
					else
						team.setKing(findRandomKing(findPositionsOfHighestNums(votes),team));
				}
			}			
		}
		Kings.getInstance().getClasses().giveKingEffects();
	}

	private Player findRandomKing(Integer[] players, Team team)
	{
		List<Integer> list = new ArrayList<Integer>();
		for(int integer : players)
		{
			if(integer > 0)
				list.add(integer);
		}
		return team.getRealPlayers()[list.get(new Random().nextInt(list.size()))];
	}

	private int findLargest(Integer[] votes)
	{
		int starter = votes[0];
		for(int i = 1; i < votes.length; i++)
		{
			if(votes[i] > starter)
				starter = votes[i];
		}
		return starter;
	}

	private boolean highestAreEqual(Integer[] numbers)
	{
		int tracker = 0;
		int temptemp = 0;
		int temp = numbers[0];
		for(int i = 1; i < numbers.length; i++)
		{
			if(numbers[i] > temp)
			{
				temp = numbers[i];
				temptemp = i;
			}
		}
		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == temp && i != temptemp)
				tracker++;
		}
		return tracker > 0;
	}

	private Integer[] findPositionsOfHighestNums(Integer[] numbers)
	{
		int largest = numbers[0];
		int tracker = 0;
		Integer array[] = new Integer[numbers.length];
		for(int i = 1; i < numbers.length; i++)
		{
			if(numbers[i] > largest)
				largest = numbers[i];
		}
		for(int i = 0; i < numbers.length; i++)
		{
			if(largest == numbers[i])
			{
				array[tracker] = i;
				tracker++;
			}
		}
		return array;
	}

	public int getVotes(Player p)
	{
		return (Kings.getInstance().getKingVotes().containsKey(p)) ? Kings.getInstance().getKingVotes().get(p) : 0;
	}

	public void init_gracecountdown(int seconds) 
	{
		Kings.getInstance().getGraceCountdown().startCountdown(seconds);
	}

	public int getEdwardKills()
	{
		return edwardkills;
	}

	public int getHenryKills()
	{
		return henrykills;
	}

	public int getCharlesKills()
	{
		return charleskills;
	}

	public int getJamesKills()
	{
		return jameskills;
	}

}

