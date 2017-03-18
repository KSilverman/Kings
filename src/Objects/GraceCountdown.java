package Objects;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import Misc.Methods;

public class GraceCountdown {
	int time;
	int id;
	int[] intervals = new int[] { 300, 270, 255, 245, 244, 243, 242, 241, 240, 180, 120, 60, 30, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };



	public void startCountdown(final int seconds)
	{
		this.time = seconds;
		this.id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Kings.getInstance(), new Runnable()
		{


			@Override
			public void run()
			{
				if (time > 0)
				{
					for (int interval : intervals)
					{
						if (time == interval)
						{
							if(time > 240 && time < 300)
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§5§lThe voting period ends in " + "§4§l" + String.valueOf(time-240) + " §5§lseconds!");
							else if(time == 240)
							{
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + ChatColor.BOLD + "Voting is now over! Your Kings are set!");
								Kings.getInstance().getGame().findKings();
								for(Team team : Kings.getInstance().getTeams())
								{
									for(Player p : team.getRealPlayers())
									{
										p.sendMessage(Kings.getInstance().getStarter() + team.toString() + ChatColor.AQUA + " Your King is " + team.getKingName());
									}
								}
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§c§lGrace period ends in " + "§6§l" + String.valueOf(4) + " §c§lminutes!");
							}
							else if(time > 60 && time < 240)
							{
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§c§lGrace period ends in " + "§6§l" + String.valueOf(time/60) + " §c§lminutes!");
							}
							else if(time == 60)
							{
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§c§lGrace period ends in " + "§6§l" + String.valueOf(1) + " §c§lminute!");
							}
							else if(time < 60 && time > 1)
							{
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§c§lGrace period ends in " + "§6§l" + String.valueOf(time) + " §c§lseconds!");
							}
							else if(time == 1)
							{
								Bukkit.broadcastMessage(Kings.getInstance().getStarter() + "§c§lGrace period ends in " + "§6§l" + String.valueOf(1) + " §c§lsecond!");
							}
							for (Player p : Bukkit.getServer().getOnlinePlayers())
								p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 2.0F);             
						}
					}
					time--;
				}
				else
				{
					cancelCountdown(id);
					Bukkit.broadcastMessage(Kings.getInstance().getStarter() + ChatColor.DARK_RED + "" + ChatColor.BOLD + "GRACE PERIOD HAS ENDED! GOOD LUCK!");
					Bukkit.broadcastMessage(
							ChatColor.BLACK + "" + ChatColor.BOLD + "--------------------\n"+ 
									ChatColor.YELLOW + "" + ChatColor.BOLD + "\nTeams' Kings!\n" + 
									Kings.getInstance().getEdward().toString() + " " + Kings.getInstance().getEdward().getKingName() + "\n" + 
									Kings.getInstance().getHenry().toString() + " " + Kings.getInstance().getHenry().getKingName() + "\n" +
									Kings.getInstance().getCharles().toString() + " " + Kings.getInstance().getCharles().getKingName() + "\n" + 
									Kings.getInstance().getJames().toString() + " " + Kings.getInstance().getJames().getKingName() + "\n" + 
									ChatColor.BLACK + "" + ChatColor.BOLD + "\n--------------------");
					for(Player p : Bukkit.getOnlinePlayers())
						Methods.setGameScoreBoard(p);
				}
			}
		}, 20L, 20L);
	}



	public void cancelCountdown(int taskID)
	{
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}

	public int getTaskID()
	{
		return id;
	}

	public void setTime(int x)
	{
		this.time = x;
	}

	public int getTime()
	{
		return time;
	}
}


