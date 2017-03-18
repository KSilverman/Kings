package Objects;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ParkBungee {

	private Plugin plugin;

	public ParkBungee(Plugin pl)
	{
		plugin = pl;
	}

	public void getAllServerNames()
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("GetServers");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sendToHub(Player p)
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
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}

}
