package de.dopebrot.cubed.command;
import de.dopebrot.cubed.Main;
import de.dopebrot.cubed.world.WorldGenerator;
import de.dopebrot.dopeapi.helper.DPCommand;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements DPCommand {

	private Main main;

	public TeleportCommand(Main main) {
		this.main = main;
	}
	@Override
	public String[] getPermissions() {
		return new String[]{"teleport.command"};
	}
	@Override
	public String getCommandName() {
		return "teleport";
	}
	@Override
	public void onDebug() {

	}
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player player) {

			for (World w : main.getServer().getWorlds()) {
				if (w != null) {
					if (w.getName().equals("cubed")) {
						player.teleport(new Location(w, player.getLocation().getX(), 257d, player.getLocation().getZ()));
					}
				}
			}
		}

		return false;
	}

}
