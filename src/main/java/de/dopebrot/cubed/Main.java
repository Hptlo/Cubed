package de.dopebrot.cubed;

import de.dopebrot.cubed.command.CubeCommand;
import de.dopebrot.cubed.world.CubeGenerator;
import de.dopebrot.cubed.world.WorldGenerator;
import de.dopebrot.dopeapi.helper.DPCommand;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main extends JavaPlugin {

	private CubeCommand cubeCommand;
	private CubeGenerator cubeGenerator;
	private World cubedWorld;
	private LanguageManager languageManager;

	@Override
	public void onEnable() {
		checkWorlds();
		registerCommands();
		this.languageManager = new LanguageManager(this);
		this.cubeGenerator = new CubeGenerator(this);
		this.cubeGenerator.setWorld(cubedWorld);
	}

	private void cmd(DPCommand command, String name) {
		Validate.notNull(command,"command can't be null!");
		Validate.notNull(name,"name can't be null!");
		Validate.notEmpty(name,"name can't be empty!");
		getCommand(name).setExecutor(command);
		getCommand(name).setTabCompleter(command);
	}

	private void registerCommands() {
		this.cubeCommand = new CubeCommand(this);
		cmd(cubeCommand, "cube");
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

	public void getResourceFile(String fileName, File file) {
		InputStream inputStream = this.getResource(fileName);
		try {
			assert inputStream != null;
			FileUtils.copyInputStreamToFile(inputStream, file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public LanguageManager languageManager() {
		return languageManager;
	}
	public CubeGenerator getCubeGenerator() {
		return cubeGenerator;
	}

}
