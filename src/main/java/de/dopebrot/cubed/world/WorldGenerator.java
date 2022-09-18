package de.dopebrot.cubed.world;

import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorldGenerator extends ChunkGenerator {

	public WorldGenerator() {
		super();
	}

	@Override
	public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
		for (int x = 0; x <= 15; x++) {
			for (int y = 0; y <= 255; y++) {
				for (int z = 0; z <= 15; z++) {
					chunkData.setBlock(x, y, z, Material.BEDROCK);
				}
			}
		}
	}

	@Override
	public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
		return new BiomeProvider() {
			@Override
			public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
				return Biome.PLAINS;
			}
			@Override
			public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
				return Collections.singletonList(Biome.PLAINS);
			}
		};
	}

	@Override
	public int getBaseHeight(@NotNull WorldInfo worldInfo, @NotNull Random random, int x, int z, @NotNull HeightMap heightMap) {
		return 0;
	}
	@Override
	public boolean canSpawn(@NotNull World world, int x, int z) {
		return true;
	}
	@Override
	public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
		return super.getDefaultPopulators(world);
	}
	@Override
	public @Nullable Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
		return new Location(world, 0, 0, 0);
	}
	@Override
	public boolean shouldGenerateNoise() {
		return false;
	}
	@Override
	public boolean shouldGenerateSurface() {
		return false;
	}
	@Override
	public boolean shouldGenerateBedrock() {
		return false;
	}
	@Override
	public boolean shouldGenerateCaves() {
		return false;
	}
	@Override
	public boolean shouldGenerateDecorations() {
		return false;
	}
	@Override
	public boolean shouldGenerateMobs() {
		return false;
	}
	@Override
	public boolean shouldGenerateStructures() {
		return false;
	}

}
