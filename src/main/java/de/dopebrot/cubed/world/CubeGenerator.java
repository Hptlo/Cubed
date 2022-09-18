package de.dopebrot.cubed.world;
import de.dopebrot.cubed.Main;
import de.dopebrot.cubed.cubes.Cube;
import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CubeGenerator {

	private Main main;
	private final ArrayList<Cube> cubeArrayList;
	private World world;

	public CubeGenerator(Main main) {
		this.main = main;
		this.cubeArrayList = new ArrayList<>();
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
					cubeArrayList.remove(c);
				}
			}
		}

		Cube n = new Cube(player.getUniqueId(), 9000, 5);
		n.setChunk(world.getChunkAt(100 - cubeArrayList.size() + 1, 100 - ((int) (cubeArrayList.size() * 0.01) + 1)));
		n.clearCube(true);
		n.setBlocks();
		n.clearCube(false);
		cubeArrayList.add(n);
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

}
