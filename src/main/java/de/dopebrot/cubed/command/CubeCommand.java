package de.dopebrot.cubed.command;
import de.dopebrot.cubed.Main;
import de.dopebrot.dopeapi.helper.DPCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CubeCommand implements DPCommand {

	private final Main main;

	public CubeCommand(Main main) {
		this.main = main;
	}
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player player) {
			if (!main.getCubeGenerator().containsPlayer(player)) {
				main.getCubeGenerator().generateNew(player);
				player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
				player.sendMessage(main.languageManager().getString("cube.new.generated"));
			}

			switch (args.length) {
				case 0:
					player.teleport(main.getCubeGenerator().getCubeFromPlayer(player).getHomeLocation());
					player.playSound(player, Sound.ENTITY_CHICKEN_EGG, 1f, 1f);
					player.sendMessage(main.languageManager().getString("cube.player.teleported"));
					break;
				case 1:
					switch (args[0].toLowerCase()) {
						case "new" -> {
							main.getCubeGenerator().generateNew(player);
							player.playSound(player, Sound.ENTITY_IRON_GOLEM_HURT, 0.6f, 1f);
							player.sendMessage(main.languageManager().getString("cube.new.generated"));
							player.teleport(main.getCubeGenerator().getCubeFromPlayer(player).getHomeLocation());
							player.playSound(player, Sound.ENTITY_CHICKEN_EGG, 0.6f, 1f);
						}
						case "calc" -> {
							float f = main.getCubeGenerator().getCubeFromPlayer(player).calculateBlocks();
							if (f >= 100f) {
								player.sendMessage(main.languageManager().getString("cube.command.calc.percent.100"));
								return true;
							}
							player.sendMessage(main.languageManager().getString("cube.command.calc.percent", new String[]{Float.toString(f)}));
							return true;
						}
						case "delete" -> {
							player.playSound(player, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.6f, 1f);
							main.getCubeGenerator().deleteCube(main.getCubeGenerator().getCubeFromPlayer(player));
							player.sendMessage(main.languageManager().getString("cube.deleted"));
						}
					}
					break;
			}
		}

		return false;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		ArrayList<String> tmp = new ArrayList<>();
		switch (args.length) {
			case 1 -> {
				tmp.add("new");
				tmp.add("delete");
				tmp.add("home");
				tmp.add("info");
				tmp.add("calc");
				tmp.add("help");
			}
			case 2 -> {
				tmp.add("test");
				tmp.add("test2");
				tmp.add("test3");
				tmp.add("test4");
				tmp.add("test5");
			}
		}
		return tmp;
	}

}
