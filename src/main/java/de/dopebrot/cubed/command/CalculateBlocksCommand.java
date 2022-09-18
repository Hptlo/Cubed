package de.dopebrot.cubed.command;
import de.dopebrot.cubed.Main;
import de.dopebrot.dopeapi.helper.DPCommand;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CalculateBlocksCommand implements DPCommand {

	private Main main;

	public CalculateBlocksCommand(Main main) {
		this.main = main;
	}
	@Override
	public String[] getPermissions() {
		return new String[]{"cubed.calculate.cube"};
	}
	@Override
	public String getCommandName() {
		return "cube-calc";
	}
	@Override
	public void onDebug() {

	}
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
			if (sender instanceof Player player) {
				main.getCubeGenerator().getCubeFromPlayer(player).calculateBlocks();
				player.playSound(player, Sound.ENTITY_CHICKEN_EGG,1f,1f);
			}

		return false;
	}

}
