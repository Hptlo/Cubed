package de.dopebrot.cubed.cubes;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BlockGenerator {

	public Material[] getBlocks(int size, int level, Random random) {
		Validate.notNull(random, "random can't be null!");
		int c = 0;

		Material[] materials = new Material[size];
		while (materials[size - 1] == null) {
			BlockType blockType = BlockType.values()[random.nextInt(BlockType.values().length)];
			if (blockType.minLevel <= level) {
				if (random.nextFloat() <= blockType.chance) {
					materials[c] = blockType.material;
					c++;
				}
			}
		}
		return materials;
	}

}

enum BlockType {

	/**
	 * chance = (random < chance)
	 */

	// STONE
	STONE(Material.STONE, 0, 1f),
	DEEPSLATE(Material.DEEPSLATE, 5, 0.2f),

	// WOOD
	OAK_LOG(Material.OAK_LOG, 0, 0.6f),
	SPRUCE_LOG(Material.SPRUCE_LOG, 1, 0.6f),
	BIRCH_LOG(Material.BIRCH_LOG, 3, 0.6f),
	JUNGLE_LOG(Material.JUNGLE_LOG, 5, 0.6f),
	ACACIA_LOG(Material.ACACIA_LOG, 10, 0.6f),
	DARK_OAK_LOG(Material.DARK_OAK_LOG, 15, 0.6f),

	// STONE ORE
	STONE_COAL_ORE(Material.COAL_ORE, 1, 0.4f),
	STONE_REDSTONE_ORE(Material.REDSTONE_ORE, 3, 0.2f),
	STONE_IRON_ORE(Material.IRON_ORE, 5, 0.1f),
	STONE_GOLD_ORE(Material.GOLD_ORE, 9, 0.07f),
	STONE_EMERALD_ORE(Material.EMERALD_ORE, 10, 0.03f),
	STONE_LAPIS_ORE(Material.LAPIS_ORE, 13, 0.02f),
	STONE_DIAMOND_ORE(Material.DIAMOND_ORE, 25, 0.01f),

	// DEEPSLATE ORE
	DEEPSLATE_COAL_ORE(Material.DEEPSLATE_COAL_ORE, 6, 0.4f),
	DEEPSLATE_REDSTONE_ORE(Material.DEEPSLATE_REDSTONE_ORE, 8, 0.2f),
	DEEPSLATE_IRON_ORE(Material.DEEPSLATE_IRON_ORE, 10, 0.1f),
	DEEPSLATE_GOLD_ORE(Material.DEEPSLATE_GOLD_ORE, 14, 0.07f),
	DEEPSLATE_EMERALD_ORE(Material.DEEPSLATE_EMERALD_ORE, 15, 0.03f),
	DEEPSLATE_LAPIS_ORE(Material.DEEPSLATE_LAPIS_ORE, 18, 0.02f),
	DEEPSLATE_DIAMOND_ORE(Material.DEEPSLATE_DIAMOND_ORE, 30, 0.01f),

	// OTHER
	DIRT(Material.DIRT,0,0.7f),
	SAND(Material.SAND,2,0.4f),
	GRAVEL(Material.GRAVEL,4,0.3f),
	RED_SAND(Material.RED_SAND,15,0.2f),


	// BONUS
	CHEST(Material.CHEST, 0, 0.001f),



	NULL(Material.AIR, 0, 0f);

	final Material material;
	final int minLevel;
	final float chance;

	BlockType(Material material, int minLevel, float chance) {
		this.material = material;
		this.minLevel = minLevel;
		this.chance = chance;
	}

}
