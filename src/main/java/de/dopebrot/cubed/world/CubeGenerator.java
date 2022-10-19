package de.dopebrot.cubed.world;
import de.dopebrot.cubed.Main;
import de.dopebrot.cubed.cubes.Cube;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CubeGenerator {

	private final Main main;
	private final ArrayList<Cube> cubeArrayList;
	private final HashMap<Player, Cube> cubeHashMap;
	private World world;
	private final Random random;

	public CubeGenerator(Main main) {
		this.main = main;
		this.cubeArrayList = new ArrayList<>();
		this.cubeHashMap = new HashMap<>();
		this.random = new Random();
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * for every 100 cubes a new cube line generates
	 */
	public void generateNew(Player player) {
		Validate.notNull(player, "player can't be null");
		for (Cube c : cubeArrayList) {
			if (c != null) {
				if (c.getBelongingPlayer() == player.getUniqueId()) {
					Chunk tmp = c.getChunk();
					for (int x = 0; x <= 15; x++) {
						for (int y = 0; y <= 255; y++) {
							for (int z = 0; z <= 15; z++) {
								tmp.getBlock(x, y, z).setType(Material.BEDROCK);
							}
						}
					}
					// override old one
					Cube n = new Cube(player.getUniqueId(), 9000, 99);
					n.setChunk(world.getChunkAt(100 - cubeArrayList.size() + 1, 100 - ((int) (cubeArrayList.size() * 0.01) + 1)));
					n.clearCube(true);
					n.setBlocks();
					n.clearCube(false);
					cubeArrayList.set(cubeArrayList.indexOf(c), n);
					cubeHashMap.put(player, n);
					return;
				}
			}
		}

		Cube n = new Cube(player.getUniqueId(), 9000, 99);
		n.setChunk(world.getChunkAt(100 - cubeArrayList.size() + 1, 100 - ((int) (cubeArrayList.size() * 0.01) + 1)));
		n.clearCube(true);
		n.setBlocks();
		n.clearCube(false);
		cubeArrayList.add(n);
		cubeHashMap.put(player, n);
	}

	public boolean containsPlayer(Player player) {
		Validate.notNull(player, "player can't be null");
		for (Cube c : cubeArrayList) {
			if (c != null) {
				if (c.getBelongingPlayer().equals(player.getUniqueId())) {
					return true;
				}
			}
		}
		return false;
	}

	public Cube getCubeFromPlayer(Player player) {
		Validate.notNull(player, "player can't be null");
		for (Cube c : cubeArrayList) {
			if (c != null) {
				if (c.getBelongingPlayer().equals(player.getUniqueId())) {
					return c;
				}
			}
		}
		return null;
	}

	public void deleteCube(Cube cube) {
		Validate.notNull(cube,"cube can't be null");

		cube.resetCube();

		this.cubeArrayList.remove(cube);
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(cube.getBelongingPlayer());
		if (offlinePlayer.getPlayer() != null) {
			this.cubeHashMap.remove(offlinePlayer.getPlayer());
		}


	}

}
