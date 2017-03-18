package Misc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import Objects.KingPlayer;
import Objects.Team;

public class Methods {
	
	public static void sendToHub(Player p)
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("Connect");
			out.writeUTF("Hub");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		p.sendPluginMessage(Kings.getInstance(), "BungeeCord", b.toByteArray());
	}
	
	public static Team getTeam(Player p)
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			if(team.getPlayers().contains(p.getName()))
				return team;
		}
		return null;
	}
	
	public static boolean isInTeam(Player p)
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			if(team.getPlayers().contains(p.getName()))
				return true;
		}
		return false;
	}
	
	public static boolean sameTeam(Player p1, Player p2)
	{
		return getTeam(p1).equals(getTeam(p2));
	}
	
	public static boolean isKing(Player p)
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			if(p.equals(team.getKing()))
				return true;
		}
		return false;
	}
	
	public static boolean isMiddle(Location loc)
	{	
		return Kings.getInstance().getCube().isLocationInCuboid(loc);
	}
	
	public static void setListName(Player player, String name)
	{
		if (name.length() > 16)
			name = name.substring(0, 16);
		player.setPlayerListName(name);
	}
	
	public static boolean modUp(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()) || Kings.getInstance().getMods().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static boolean vipUp(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()) || Kings.getInstance().getMods().contains(p.getName()) || Kings.getInstance().getVips().contains(p.getName()) || Kings.getInstance().getBuilders().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static boolean coderUp(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static boolean godUp(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()) || Kings.getInstance().getMods().contains(p.getName()) || Kings.getInstance().getVips().contains(p.getName()) || Kings.getInstance().getBuilders().contains(p.getName()) || Kings.getInstance().getGods().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static boolean legendUp(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()) || Kings.getInstance().getMods().contains(p.getName()) || Kings.getInstance().getVips().contains(p.getName()) || Kings.getInstance().getBuilders().contains(p.getName()) || Kings.getInstance().getGods().contains(p.getName()) || Kings.getInstance().getLegends().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static boolean isRanked(Player p)
	{
		if(Kings.getInstance().getOwners().contains(p.getName()) || Kings.getInstance().getAdmins().contains(p.getName()) || Kings.getInstance().getCoders().contains(p.getName()) || Kings.getInstance().getMods().contains(p.getName()) || Kings.getInstance().getVips().contains(p.getName()) || Kings.getInstance().getBuilders().contains(p.getName()) || Kings.getInstance().getGods().contains(p.getName()) || Kings.getInstance().getLegends().contains(p.getName()) || Kings.getInstance().getMasters().contains(p.getName()))
			return true;
		else
			return false;
	}
	
	public static ItemStack setName(ItemStack is, String name, ChatColor colour)
	{
		    ItemMeta im = is.getItemMeta();
		    im.setDisplayName(colour + name);
		    is.setItemMeta(im);
		    return is;
	}
	  
	public static ItemStack setColor(ItemStack is, String Name, Color color, ChatColor colorr)
	{
		  LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
		  im.setColor(color);
		  im.setDisplayName(colorr + Name);
		  is.setItemMeta(im);
		  return is;
	}
	
	public static void openArrow(Player p)
	{
		 Inventory inv = Bukkit.getServer().createInventory(p, 9, ChatColor.RED + "Kit Selection");
		 if(isRanked(p))
		 {
			 inv.setItem(1, setName(new ItemStack(Material.CHAINMAIL_CHESTPLATE), "Juggernaut", ChatColor.GOLD));
			 inv.setItem(3, setName(new ItemStack(Material.STONE_PICKAXE), "Juggler", ChatColor.YELLOW));
			 inv.setItem(5, setName(new ItemStack(Material.DIAMOND_BOOTS), "Flash", ChatColor.RED));
			 inv.setItem(7, setName(new ItemStack(Material.DIAMOND_SWORD), "Knight", ChatColor.GRAY));
		 }
		 else
		 {
			 inv.setItem(3, setName(new ItemStack(Material.CHAINMAIL_CHESTPLATE), "Juggernaut", ChatColor.GOLD));
			 inv.setItem(5, setName(new ItemStack(Material.STONE_PICKAXE), "Juggler", ChatColor.YELLOW));
		 }
		 p.openInventory(inv);
	}
	
	public static ChatColor getRankColor(Player player)
	{
		if(Kings.getInstance().getOwners().contains(player.getName()))
			return ChatColor.DARK_RED;
		else if(Kings.getInstance().getAdmins().contains(player.getName()))
			return ChatColor.RED;
		else if(Kings.getInstance().getMods().contains(player.getName()))
			return ChatColor.DARK_AQUA;
		else if(Kings.getInstance().getCoders().contains(player.getName()))
			return ChatColor.RED;
		else if(Kings.getInstance().getBuilders().contains(player.getName()))
			return ChatColor.LIGHT_PURPLE;
		else if(Kings.getInstance().getVips().contains(player.getName()))
			return ChatColor.DARK_PURPLE;
		else if(Kings.getInstance().getGods().contains(player.getName()))
			return ChatColor.AQUA;
		else if(Kings.getInstance().getLegends().contains(player.getName()))
			return ChatColor.GOLD;
		else if(Kings.getInstance().getMasters().contains(player.getName()))
			return ChatColor.GRAY;
		return null;
	}
	  
	  public static String getRankName(String player)
	  {
			if(Kings.getInstance().getMasters().contains(player))
				return ChatColor.DARK_GRAY + "MASTER";
			if(Kings.getInstance().getLegends().contains(player))
				return ChatColor.GOLD + "LEGEND";
			if(Kings.getInstance().getGods().contains(player))
				return ChatColor.AQUA + "GOD";
			if(Kings.getInstance().getOwners().contains(player))
				return ChatColor.DARK_RED + "OWNER";
			if(Kings.getInstance().getAdmins().contains(player))
				return ChatColor.RED + "ADMIN";
			if(Kings.getInstance().getMods().contains(player))
				return ChatColor.DARK_AQUA + "MOD";
			if(Kings.getInstance().getVips().contains(player))
				return ChatColor.DARK_PURPLE + "VIP";
			if(Kings.getInstance().getBuilders().contains(player))
				return ChatColor.LIGHT_PURPLE + "MAP MAKER";
			if(Kings.getInstance().getCoders().contains(player))
				return ChatColor.RED + "CODER";
			return ChatColor.GREEN + "NONE";
		}
	  
	    private static void unloadMap(String mapname)
	    {
	        if(!Bukkit.getServer().unloadWorld(Bukkit.getWorld(mapname), false))
	        	Bukkit.getServer().unloadWorld(Bukkit.getWorld(mapname), false);
	    }

	    private static void loadMap(String mapname)
	    {
	    	World w = Bukkit.getServer().createWorld(new WorldCreator(mapname));
	    	w.setAutoSave(false);
	    }
	 
	    private static void rollback(String mapname)
	    {
	        unloadMap(mapname);
	        loadMap(mapname);
	    }
	    
	    public static void deletePlayerDat(String worldName)
	    {
	    	File playerFilesDir = new File(worldName + "/playerdata");
	    	if(playerFilesDir.isDirectory())
	    	{
	    		String[] playerDats = playerFilesDir.list();
	    		for (int i = 0; i < playerDats.length; i++) 
	    		{
	    			File datFile = new File(playerFilesDir, playerDats[i]); 
	    			datFile.delete();
	    			//System.out.println("[Kings] Deleted player data file for " + Kings.getInstance().getServer().getOfflinePlayer(playerDats[i]) + ".dat !");
	    		}
	    	}
	    }
	    
	    public static void deleteUID(String worldName)
	    {
	    	File worldFile = new File(worldName);
	    	File datFile = new File(worldFile, "uid.dat");
	    	datFile.delete();
	    }
	    
	    public static void doMapStuff()
	    {
			unloadMap("Kings");
			loadMap("Kings");
			rollback("Kings");
			unloadMap("world");
			loadMap("world");
			rollback("world");
			deletePlayerDat("Kings");
			deletePlayerDat("world");
			deleteUID("Kings");
			deleteUID("wolrd");	
	    }
	    
	    public static void clearAllLists()
		{
			Kings.getInstance().getOwners().clear();
			Kings.getInstance().getAdmins().clear();
			Kings.getInstance().getCoders().clear();
			Kings.getInstance().getMods().clear();
			Kings.getInstance().getVips().clear();
			Kings.getInstance().getBuilders().clear();
			Kings.getInstance().getGods().clear();
			Kings.getInstance().getLegends().clear();
			Kings.getInstance().getMasters().clear();
			Kings.getInstance().getSpectators().clear();
			Kings.getInstance().getVoted().clear();
			Kings.getInstance().getKingPlayers().clear();
			Kings.getInstance().getKingVotes().clear();
			
			for(Team team : Kings.getInstance().getTeams())
				team.getPlayers().clear();
			
			
			Kings.getInstance().getTeams().clear();
		}
	    
	    @SuppressWarnings("deprecation")
		public static void setGameScoreBoard(Player player)
		{
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Kings", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "§nKings" + ChatColor.RESET);
			Score teamedward = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getEdward().toString()));
			Score edwardking = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getEdward().getKingName()));
			Score edwardkills = objective.getScore(Bukkit.getOfflinePlayer(Kings.getInstance().getEdward().getChatColor() + "§lKILLS: " + "§4§l" + String.valueOf(Kings.getInstance().getGame().getEdwardKills())));
			Score blank1 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLACK + "=-=-=-=-=-=-="));
			Score teamhenry = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getHenry().toString()));
			Score henryking = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getHenry().getKingName()));
			Score henrykills = objective.getScore(Bukkit.getOfflinePlayer(Kings.getInstance().getHenry().getChatColor() + "§lKILLS: " + "§4§l" + String.valueOf(Kings.getInstance().getGame().getHenryKills())));
			Score blank2 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY + "=-=-=-=-=-=-="));
			Score teamcharles = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getCharles().toString()));
			Score charlesking = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getCharles().getKingName()));
			Score charleskills = objective.getScore(Bukkit.getOfflinePlayer(Kings.getInstance().getCharles().getChatColor() + "§lKILLS: " + "§4§l" + String.valueOf(Kings.getInstance().getGame().getCharlesKills())));
			Score blank3 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "=-=-=-=-=-=-="));
			Score teamjames = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getJames().toString()));
			Score jamesking = objective.getScore(Bukkit.getOfflinePlayer("§l" + Kings.getInstance().getJames().getKingName()));
			Score jameskills = objective.getScore(Bukkit.getOfflinePlayer(Kings.getInstance().getJames().getChatColor() + "§lKILLS: " + "§4§l" + String.valueOf(Kings.getInstance().getGame().getJamesKills())));
			teamedward.setScore(15);
			edwardking.setScore(14);
			edwardkills.setScore(13);
			blank1.setScore(12);
			teamhenry.setScore(11);
			henryking.setScore(10);
			henrykills.setScore(9);
			blank2.setScore(8);
			teamcharles.setScore(7);
			charlesking.setScore(6);
			charleskills.setScore(5);
			blank3.setScore(4);
			teamjames.setScore(3);
			jamesking.setScore(2);
			jameskills.setScore(1);
			player.setScoreboard(board);
		}
	    
	    @SuppressWarnings("deprecation")
		public static void setLobbyScoreboard(Player p)
	    {
	    	ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Kings", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "§nKings");
			Score time = objective.getScore(Bukkit.getOfflinePlayer("§b§lTIME:"));
			Score timeint = objective.getScore(Bukkit.getOfflinePlayer("§9§l" + String.valueOf(Kings.getInstance().getLobbyCountdown().getTime())));
			Score blank1 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLACK + "=-=-=-=-=-=-="));
			Score points = objective.getScore(Bukkit.getOfflinePlayer("§2§lYOUR POINTS:"));
			Score pointsint = objective.getScore(Bukkit.getOfflinePlayer("§l" + KingPlayer.getKingPlayer(p).getPoints()));
			Score blank2 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "=-=-=-=-=-=-="));
			Score kd = objective.getScore(Bukkit.getOfflinePlayer("§c§lYOUR K/D:"));
			Score kdint = objective.getScore(Bukkit.getOfflinePlayer("§4§l" + String.valueOf(roundInt((KingPlayer.getKingPlayer(p).getKills() / KingPlayer.getKingPlayer(p).getDeaths()), 3))));
			Score blank3 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY + "=-=-=-=-=-=-="));
			time.setScore(9);
			timeint.setScore(8);
			blank1.setScore(7);
			points.setScore(6);
			pointsint.setScore(5);
			blank2.setScore(4);
			kd.setScore(3);
			kdint.setScore(2);
			blank3.setScore(1);
			p.setScoreboard(board);
	    }
	    
		public static void shootFirework(String world, Location location, Color color, Type type, boolean flicker, boolean trail, int power)
		{
			Firework fw = (Firework) Bukkit.getWorld(world).spawn(location, Firework.class);
			FireworkMeta fm = fw.getFireworkMeta();
			FireworkEffect effect = FireworkEffect.builder().flicker(flicker).withColor(color).with(type).trail(trail).build();
			fm.addEffect(effect);
			fm.setPower(power);
			fw.setFireworkMeta(fm);
		}
	    
	    private static double roundInt(double num, int slot) 
	    {
			int factor = (int) Math.pow(10, slot + 1);
			int temp = (int) (num * (double) factor);
			int digit = temp % 10; 
			temp = temp / 10;

			if (digit >= 5)
				temp++;

			return (double) temp / Math.pow(10, slot);
		}
	    
	    public static void doSpectatorDance(Player p)
	    {
	    	Kings.getInstance().getSpectators().add(p.getName());
	    	p.getInventory().clear();
            p.getActivePotionEffects().clear();
            p.setHealth(20.0);
            p.setFoodLevel(20);
            p.getInventory().setArmorContents(
					new ItemStack[] { new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR),
							new ItemStack(Material.AIR) });
            setListName(p, ChatColor.BLACK + p.getName());
            p.setGameMode(GameMode.CREATIVE);
            p.getInventory().setItem(0, setName(new ItemStack(Material.COMPASS), "Return to hub", ChatColor.BLUE));
            p.getInventory().setItem(1, setName(new ItemStack(Material.MAP), "§lSpectator Teleporter", ChatColor.WHITE));
            for(int i = 2; i < 8; i++)
            	p.getInventory().setItem(i, setName(new ItemStack(Material.GOLD_NUGGET), "Your riches", ChatColor.GOLD));
            p.teleport(new Location(Bukkit.getWorld("Kings"), -411, 45, 303));
            for(Player player : Bukkit.getOnlinePlayers())
            	player.hidePlayer(p);
            p.sendMessage(Kings.getInstance().getStarter() + ChatColor.GOLD + "" + ChatColor.BOLD + "You are a spectator!");
	    }
	    
	    public static int getSizeForMap()
	    {
	        int size = 0;
	        for(Team t : Kings.getInstance().getTeams())
	        	size += t.getPlayers().size();
	        while(!(size % 9 == 0))
	        {
	            ++size;
	        }
	        return (size > 54) ? 54 : size;
	    }
	    
	    public static String getTextBlock(int amount, ChatColor color)
		{
			List<String> temp = new ArrayList<String>();
			for(int i = 0; i < amount; i++)
				temp.add(color + "\u2588");
			String result = "";
			for(String s : temp)
				result += s;
			return result;
		}

}

