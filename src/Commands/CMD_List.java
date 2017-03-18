package Commands;

import me.BajanAmerican.Kings.Kings;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import Commands.SimpleCommand.CommandHandler;

public class CMD_List {
	
	@CommandHandler(name = "list")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.isOp())
			{
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("GetServers");
                p.sendPluginMessage(Kings.getInstance(), "BungeeCord", out.toByteArray());
			}
		}
    }

}
