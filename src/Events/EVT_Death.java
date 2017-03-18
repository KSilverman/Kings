package Events;

import me.BajanAmerican.Kings.Kings;
import net.minecraft.server.v1_7_R3.EnumClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import Misc.Methods;
import Objects.KingPlayer;
import Objects.Team;

public class EVT_Death implements Listener
{
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent event)
	{
		if(!(event.getEntity() instanceof Player))
			return;
		final Player victim = (Player) event.getEntity();
		KingPlayer kingvictim = KingPlayer.getKingPlayer(victim);

		event.getDrops().clear();
		event.setDroppedExp(0);

		event.setDeathMessage(null);

		if(!(Methods.isKing(victim)))
		{
			Methods.getTeam(victim).getPlayers().remove(victim.getName());
			Bukkit.broadcastMessage(Kings.getInstance().getStarter() + Methods.getTeam(victim).getChatColor() + victim.getName() + ChatColor.RED + " has died from team " + 
					Methods.getTeam(victim).toString() + ChatColor.GRAY + "[" + ChatColor.AQUA + String.valueOf(Methods.getTeam(victim).getPlayers().size()) + ChatColor.GRAY + "]");
			Methods.doSpectatorDance(victim);
			if(victim.getKiller() instanceof Player)
			{
				Player killer = (Player) victim.getKiller();
				KingPlayer kingattacker = KingPlayer.getKingPlayer(killer);
				kingattacker.setPoints(kingattacker.getPoints() + 5);
				kingattacker.setKills(kingattacker.getKills() + 1);
				kingvictim.setDeaths(kingvictim.getDeaths() + 1);
				kingvictim.setPoints(kingvictim.getPoints() - 3);
			}
			else
			{
				kingvictim.setDeaths(kingvictim.getDeaths() + 1);
				kingvictim.setPoints(kingvictim.getPoints() - 3);
			}
		}
		else
		{
			if(victim.getKiller() instanceof Player)
			{
				Player killer = (Player) victim.getKiller();
				KingPlayer kingattacker = KingPlayer.getKingPlayer(killer);
				kingattacker.setPoints(kingattacker.getPoints() + 5);
				kingattacker.setKills(kingattacker.getKills() + 1);
				kingvictim.setDeaths(kingvictim.getDeaths() + 1);
				kingvictim.setPoints(kingvictim.getPoints() - 3);
			}
			else
			{
				kingvictim.setDeaths(kingvictim.getDeaths() + 1);
				kingvictim.setPoints(kingvictim.getPoints() - 3);
			}
			Team team = Methods.getTeam(victim);
			for(Player p : team.getRealPlayers())
			{
				p.sendMessage(Kings.getInstance().getStarter() + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "YOUR KING HAS DIED!");
				team.getPlayers().remove(p.getName());
				Methods.doSpectatorDance(p);
				KingPlayer kp = KingPlayer.getKingPlayer(p);
				if(!(Methods.isKing(p)))
				{
					kp.setDeaths(kp.getDeaths() + 1);
					kp.setPoints(kp.getPoints() - 3);
				}
			}
			Kings.getInstance().getTeams().remove(team);
			Bukkit.broadcastMessage(Kings.getInstance().getStarter() + ChatColor.BOLD + "" + ChatColor.DARK_RED + "TEAM " + ChatColor.BOLD + team.toString() + ChatColor.DARK_RED + "" + ChatColor.BOLD + " HAS BEEN ELIMINATED!!!");
		}

		if(Kings.getInstance().getTeams().size() == 1)
		{
			Team winners = null;
			for(int i = 0; i < Kings.getInstance().getTeams().size(); i++)
				winners = Kings.getInstance().getTeams().get(i);
			Bukkit.broadcastMessage(Kings.getInstance().getStarter() + winners.getChatColor() + "" + ChatColor.BOLD + "TEAM " + winners.toString().toUpperCase() + " HAS WON!!!");
			Kings.getInstance().getGame().end();
		}

		Kings.getInstance().getServer().getScheduler().runTaskLater(Kings.getInstance(), new Runnable() 
		{
			public void run() 
			{
				((CraftPlayer)event.getEntity()).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			}
		}
		, 2L);

		for(Player p : Bukkit.getOnlinePlayers())
			Methods.setGameScoreBoard(p);
	}
}
