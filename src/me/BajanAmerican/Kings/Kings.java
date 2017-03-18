package me.BajanAmerican.Kings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import Commands.CMD_Hub;
import Commands.CMD_List;
import Commands.CMD_Time;
import Commands.CMD_Vote;
import Commands.SimpleCommand;
import Events.EVT_Block;
import Events.EVT_Chat;
import Events.EVT_Damage;
import Events.EVT_Drop;
import Events.EVT_Health;
import Events.EVT_Hunger;
import Events.EVT_Interact;
import Events.EVT_InvClick;
import Events.EVT_Join;
import Events.EVT_Move;
import Events.EVT_Ping;
import Events.EVT_PreJoin;
import Events.EVT_Pvp;
import Events.EVT_Tag;
import Events.EVT_Weather;
import Misc.Methods;
import Objects.Classes;
import Objects.Cuboid;
import Objects.Game;
import Objects.GameState;
import Objects.GraceCountdown;
import Objects.KingPlayer;
import Objects.LobbyCountdown;
import Objects.SQL;
import Objects.Team;

public class Kings extends JavaPlugin implements PluginMessageListener
{
	private static Kings instance;

	public List<String> test = new ArrayList<String>();

	List<String> owners;
	List<String> admins;
	List<String> mods;
	List<String> coders;
	List<String> builders;
	List<String> vips;
	List<String> gods;
	List<String> legends;
	List<String> masters;
	List<String> specs;
	List<String> voted;
	List<Team> teams;
	Map<String, Integer> playerkillstreak;
	Map<String, KingPlayer> kingplayers;
	Map<Player, Integer> kingvotes;

	Team edward;
	Team henry;
	Team charles;
	Team james;

	SQL sql;

	Game game;

	GameState gamestate;

	Classes classes;

	LobbyCountdown lbd;
	GraceCountdown gcd;

	Scoreboard scoreboard;

	Cuboid cub;

	@Override
	public void onEnable()
	{
		instance = this;
		teams = new ArrayList<Team>();
		voted = new ArrayList<String>();
		kingvotes = new HashMap<Player, Integer>();
		kingplayers = new HashMap<String, KingPlayer>();
		playerkillstreak = new HashMap<String, Integer>();
		owners = new ArrayList<String>();
		admins = new ArrayList<String>();
		mods = new ArrayList<String>();
		coders = new ArrayList<String>();
		builders = new ArrayList<String>();
		vips = new ArrayList<String>();
		gods = new ArrayList<String>();
		legends = new ArrayList<String>();
		masters = new ArrayList<String>();
		specs = new ArrayList<String>();
		sql = new SQL();
		game = new Game();
		gamestate = new GameState();
		classes = new Classes();
		lbd = new LobbyCountdown();
		gcd = new GraceCountdown();
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		edward = new Team("Edward", ChatColor.BLUE);
		henry = new Team("Henry", ChatColor.YELLOW);
		charles = new Team("Charles", ChatColor.RED);
		james = new Team("James", ChatColor.GREEN);
		Methods.clearAllLists();
		Methods.doMapStuff();
		gamestate.setInLobby();
		init_teams();
		init_commands();
		init_events();
		init_lobbycountdown(120);
		cub = new Cuboid(new Location(Bukkit.getWorld("Kings"), -457, 0, 352), new Location(Bukkit.getWorld("Kings"), -370, 256, 266));
		System.out.println("ENABLED!");
	}

	@Override
	public void onDisable()
	{
		try
		{
			if(sql.getConnection() != null && !sql.getConnection().isClosed())
			{
				sql.getConnection().close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		Methods.clearAllLists();
		Methods.doMapStuff();
		System.out.println("DISABLED!");
	}

	private void init_lobbycountdown(int seconds)
	{
		getLobbyCountdown().startCountdown(seconds);
	}

	private void init_teams()
	{
		teams.add(edward);
		teams.add(henry);
		teams.add(charles);
		teams.add(james);
	}

	private void init_commands()
	{
		SimpleCommand.registerCommands(this, new CMD_Vote());
		SimpleCommand.registerCommands(this, new CMD_Hub());
		SimpleCommand.registerCommands(this, new CMD_Time());
		SimpleCommand.registerCommands(this, new CMD_List());
	}

	private void init_events()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EVT_Weather(), this);
		pm.registerEvents(new EVT_Damage(), this);
		pm.registerEvents(new EVT_Drop(), this);
		pm.registerEvents(new EVT_Tag(), this);
		pm.registerEvents(new EVT_Ping(), this);
		pm.registerEvents(new EVT_PreJoin(), this);
		pm.registerEvents(new EVT_Chat(), this);
		pm.registerEvents(new EVT_Hunger(), this);
		pm.registerEvents(new EVT_Pvp(), this);
		pm.registerEvents(new EVT_Move(), this);
		pm.registerEvents(new EVT_Block(), this);
		pm.registerEvents(new EVT_Health(), this);
		pm.registerEvents(new EVT_Join(), this);
		pm.registerEvents(new EVT_Interact(), this);
		pm.registerEvents(new EVT_InvClick(), this);
	}

	public static Kings getInstance()
	{
		return instance;
	}

	public String getStarter()
	{
		return ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "[Kings] ";
	}

	public String getPermMsg()
	{
		return ChatColor.DARK_RED + "You do not have permission to execute this command.";
	}

	public List<String> getOwners()
	{
		return owners;
	}

	public List<String> getAdmins()
	{
		return admins;
	}

	public List<String> getMods()
	{
		return mods;
	}

	public List<String> getBuilders()
	{
		return builders;
	}

	public List<String> getCoders()
	{
		return coders;
	}

	public List<String> getVips()
	{
		return vips;
	}

	public List<String> getGods()
	{
		return gods;
	}

	public List<String> getLegends()
	{
		return legends;
	}

	public List<String> getMasters()
	{
		return masters;
	}

	public List<String> getSpectators()
	{
		return specs;
	}

	public Map<String, KingPlayer> getKingPlayers()
	{
		return kingplayers;
	}

	public Map<String, Integer> getPlayerKillStreaks()
	{
		return playerkillstreak;
	}

	public List<Team> getTeams()
	{
		return teams;
	}

	public Team getEdward()
	{
		return edward;
	}

	public Team getHenry()
	{
		return henry;
	}

	public Team getCharles()
	{
		return charles;
	}

	public Team getJames()
	{
		return james;
	}

	public SQL getMySQL()
	{
		return sql;
	}

	public Game getGame()
	{
		return game;
	}

	public GameState getGameState()
	{
		return gamestate;
	}

	public Classes getClasses()
	{
		return classes;
	}

	public LobbyCountdown getLobbyCountdown()
	{
		return lbd;
	}

	public GraceCountdown getGraceCountdown()
	{
		return gcd;
	}

	public Map<Player, Integer> getKingVotes()
	{
		return kingvotes;
	}

	public List<String> getVoted()
	{
		return voted;
	}

	public Scoreboard getScoreboard()
	{
		return scoreboard;
	}

	public Cuboid getCube()
	{
		return cub;
	}

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] message)
	{
		if (!channel.equals("BungeeCord"))
		{
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("GetServers"))
		{
			String[] serverList = in.readUTF().split(", ");
			String result = "";
			for(String s : serverList)
				result += ChatColor.GREEN + s + ", ";
			p.sendMessage(result);
		}
	}
}

