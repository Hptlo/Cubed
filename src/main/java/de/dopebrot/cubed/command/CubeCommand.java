package de.dopebrot.cubed.command;
import de.dopebrot.cubed.Main;
import de.dopebrot.dopeapi.helper.DPCommand;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CubeCommand implements DPCommand {

	private Main main;

	public CubeCommand(Main main) {
		this.main = main;
	}
	@Override
	public String[] getPermissions() {
		return new String[]{"cube.home"};
	}
	@Override
	public String getCommandName() {
		return "cube";
	}
	@Override
	public void onDebug() {

	}
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player player) {
			if (!main.getCubeGenerator().containsPlayer(player)) {
				main.getCubeGenerator().generateNew(player);
			}

			switch (args.length) {
				case 0:
					player.teleport(main.getCubeGenerator().getCubeFromPlayer(player).getHomeLocation());
					player.playSound(player, Sound.ENTITY_CHICKEN_EGG, 1f, 1f);
					break;
				case 1:
					switch (args[0].toLowerCase()) {
						case "new" -> {
							main.getCubeGenerator().generateNew(player);
							player.playSound(player, Sound.ENTITY_IRON_GOLEM_HURT, 0.6f, 1f);
						}
						case "delete" ->
								main.getCubeGenerator().generateNew(player);
					}
					break;
			}
		}

		return false;
	}

}
