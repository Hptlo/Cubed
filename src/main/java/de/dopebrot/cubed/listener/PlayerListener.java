package de.dopebrot.cubed.listener;
import de.dopebrot.cubed.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {

	private Main main;

	public PlayerListener(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerBreakBlock(BlockDamageEvent event) {
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if (main.getCubeGenerator().containsPlayer(event.getPlayer())) {
				if (event.getBlock().getWorld() == main.getCubeGenerator().getWorld()) {
					if (event.getBlock().getLocation().getChunk() != main.getCubeGenerator().getCubeFromPlayer(event.getPlayer()).getChunk()) {
						event.setCancelled(false);
					}
				}
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent event) {
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if (main.getCubeGenerator().containsPlayer(event.getPlayer())) {
				if (event.getBlock().getWorld() == main.getCubeGenerator().getWorld()) {
					if (event.getBlock().getLocation().getChunk() != main.getCubeGenerator().getCubeFromPlayer(event.getPlayer()).getChunk()) {
						event.setCancelled(false);
					}
				}
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPlaceMultipleBlock(BlockMultiPlaceEvent event) {
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if (main.getCubeGenerator().containsPlayer(event.getPlayer())) {
				if (event.getBlock().getWorld() == main.getCubeGenerator().getWorld()) {
					if (event.getBlock().getLocation().getChunk() != main.getCubeGenerator().getCubeFromPlayer(event.getPlayer()).getChunk()) {
						event.setCancelled(false);
					}
				}
			}
			event.setCancelled(true);
		}
	}




}
