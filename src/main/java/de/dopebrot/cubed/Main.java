package de.dopebrot.cubed;

import de.dopebrot.cubed.command.CalculateBlocksCommand;
import de.dopebrot.cubed.command.CubeCommand;
import de.dopebrot.cubed.command.TeleportCommand;
import de.dopebrot.cubed.world.CubeGenerator;
import de.dopebrot.cubed.world.WorldGenerator;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

	private TeleportCommand teleportCommand;
	private CalculateBlocksCommand calculateBlocksCommand;
	private CubeCommand cubeCommand;
	private CubeGenerator cubeGenerator;
	private World cubedWorld;


	@Override
	public void onEnable() {
		checkWorlds();
		registerCommands();

		this.cubeGenerator = new CubeGenerator(this);
		this.cubeGenerator.setWorld(cubedWorld);
	}

	private void registerCommands() {
		this.teleportCommand = new TeleportCommand(this);
		this.calculateBlocksCommand = new CalculateBlocksCommand(this);
		this.cubeCommand = new CubeCommand(this);
		getCommand(teleportCommand.getCommandName()).setExecutor(teleportCommand);
		getCommand(calculateBlocksCommand.getCommandName()).setExecutor(calculateBlocksCommand);
		getCommand(cubeCommand.getCommandName()).setExecutor(cubeCommand);
	}

	private void checkWorlds() {
		for (World w : getServer().getWorlds()) {
			if (w != null) {
				if (w.getName().equals("cubed")) {
					getServer().unloadWorld(w, false);
				}
			}
		}
		cubedWorld = getServer().createWorld(new WorldCreator("cubed").generator(new WorldGenerator()));
	}

	@Override
	public void onDisable() {

	}

	public CubeGenerator getCubeGenerator() {
		return cubeGenerator;
	}

}
