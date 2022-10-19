package de.dopebrot.cubed.cubes;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

public class Cube {

	private final UUID belongingPlayer;
	private final int decayTime;
	private final int level;
	private final int origin = 8;
	private int size;
	private Location homeLocation;

	private Chunk chunk;

	public Cube(UUID belongingPlayer, int decayTime, int level) {
		this.belongingPlayer = belongingPlayer;
		this.decayTime = decayTime;
		this.level = level;
		this.size = 2 + level;

		if (size > 250) {
			size = 250;
		}
	}

	public void setChunk(Chunk chunk) {
		Validate.notNull(chunk, "chunk can't be null");
		this.chunk = chunk;
		this.homeLocation = chunk.getBlock(origin, 1, origin).getLocation();
		this.homeLocation.setYaw(90f);
	}

	public void resetCube() {
		for (int x = 1; x <= 14; x++) {
			for (int z = 1; z <= 14; z++) {
				for (int y = 1; y <= size; y++) {
					chunk.getBlock(x, y, z).setType(Material.BEDROCK);
				}
			}
		}
	}

	public void clearCube(boolean whole) {
		if (whole) {
			for (int x = 1; x <= 14; x++) {
				for (int z = 1; z <= 14; z++) {
					for (int y = 1; y <= size; y++) {
						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
				}
			}
		} else {
			for (int x = 7; x <= 8; x++) {
				for (int z = 7; z <= 8; z++) {
					for (int y = 1; y <= 2; y++) {
						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
				}
			}
		}
	}

	public float calculateBlocks() {
		int count = 0;
		for (int x = 1; x <= 14; x++) {
			for (int z = 1; z <= 14; z++) {
				for (int y = 1; y <= size; y++) {
					if (chunk.getBlock(x, y, z).getType() == Material.AIR) {
						count++;
					}
				}
			}
		}
//		Bukkit.getLogger().log(Level.INFO, "Precent of blocks destroyed: " + (Math.round(((count / (14f * 14f * size)) * 100f) * 100f) / 100f) + "%");
		return (Math.round(((count / (14f * 14f * size)) * 100f) * 100f) / 100f);
	}

	public void setBlocks() {
		Material[] material = new BlockGenerator().getBlocks(14 * 14 * size + 1, level, new Random());
		int counter = 0;
		for (int x = 1; x <= 14; x++) {
			for (int z = 1; z <= 14; z++) {
				for (int y = 1; y <= size; y++) {
					counter++;
					chunk.getBlock(x, y, z).setType(material[counter]);
					if (material[counter] == Material.CHEST) {
						if (chunk.getBlock(x, y, z) instanceof Chest chest) {
							chest.getBlockInventory().setItem(0, new ItemStack(Material.STONE));
						}
					}
				}
			}
		}
	}


	public int getLevel() {
		return level;
	}
	public UUID getBelongingPlayer() {
		return belongingPlayer;
	}
	public int getDecayTime() {
		return decayTime;
	}

	public Location getHomeLocation() {
		return homeLocation;
	}

	public Chunk getChunk() {
		return chunk;
	}

}
