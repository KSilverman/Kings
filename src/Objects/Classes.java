package Objects;

import java.util.ArrayList;
import java.util.List;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Misc.Methods;

public class Classes {

	List<String> knights;
	List<String> flashes;
	List<String> jugs;
	List<String> jugglers;

	public Classes()
	{
		knights = new ArrayList<String>();
		flashes = new ArrayList<String>();
		jugs = new ArrayList<String>();
		jugglers = new ArrayList<String>();
	}


	public void giveDefaultClass()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.getInventory().addItem(Methods.setName(new ItemStack(Material.BOW), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s bow", Methods.getTeam(p).getChatColor()));
			p.getInventory().addItem(Methods.setName(new ItemStack(Material.IRON_SWORD), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s dagger", Methods.getTeam(p).getChatColor()));
			p.getInventory().addItem(Methods.setName(new ItemStack(Material.ARROW, 64), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s bows", Methods.getTeam(p).getChatColor()));
			p.getInventory().addItem(Methods.setName(new ItemStack(Material.ARROW, 64), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s bows", Methods.getTeam(p).getChatColor()));
			p.getInventory().setHelmet(Methods.setName(new ItemStack(Material.IRON_HELMET), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s helmet", Methods.getTeam(p).getChatColor()));
			p.getInventory().setChestplate(Methods.setName(new ItemStack(Material.IRON_CHESTPLATE), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s chestplate", Methods.getTeam(p).getChatColor()));
			p.getInventory().setLeggings(Methods.setName(new ItemStack(Material.IRON_LEGGINGS), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s pants", Methods.getTeam(p).getChatColor()));
			p.getInventory().setBoots(Methods.setName(new ItemStack(Material.IRON_BOOTS), Methods.getTeam(p).toString().substring(1, Methods.getTeam(p).toString().length() - 1) + "'s shoes", Methods.getTeam(p).getChatColor()));
			p.setLevel(20);
		}
	}

	public void giveKingEffects()
	{
		for(Team team : Kings.getInstance().getTeams())
		{
			team.getKing().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
			team.getKing().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
			team.getKing().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			team.getKing().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
			team.getKing().setMaxHealth(40.0);
			team.getKing().setHealth(40.0);
		}
	}

	@SuppressWarnings("deprecation")
	public void giveSpecialEffects()
	{
		for(String s : knights)
			Bukkit.getPlayer(s).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
		for(String s : flashes)
			Bukkit.getPlayer(s).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		for(String s : jugs)
			Bukkit.getPlayer(s).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
		for(String s : jugglers)
			Bukkit.getPlayer(s).addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
	}

	public void giveDefaultClasses()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if ((!knights.contains(player.getName())) && 
					(!flashes.contains(player.getName())) && 
					(!jugs.contains(player.getName())) && 
					(!jugglers.contains(player.getName())))
				jugglers.add(player.getName());
		}
	}

	public void giveLobbyGoodies()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.getInventory().setItem(8, Methods.setName(new ItemStack(Material.COMPASS), "Return to hub", ChatColor.BLUE));
			p.getInventory().setItem(0, Methods.setName(new ItemStack(Material.ARROW), "§lKit Selection", ChatColor.RED));
		}
	}

	public void removeFromClass(String player)
	{
		if(knights.contains(player))
			knights.remove(player);
		if(flashes.contains(player))
			flashes.remove(player);
		if(jugs.contains(player))
			jugs.remove(player);
		if(jugglers.contains(player))
			jugglers.remove(player);
	}

	public static void removeOnePotion(Player player, PotionEffectType pet)
	{
		for (PotionEffect effect : player.getActivePotionEffects())
		{
			if(effect.getType().equals(pet))
				player.removePotionEffect(pet);
		}
	}

	public List<String> getKnights()
	{
		return knights;
	}

	public List<String> getFlashes()
	{
		return flashes;
	}

	public List<String> getJugs()
	{
		return jugs;
	}

	public List<String> getJugglers()
	{
		return jugglers;
	}
}