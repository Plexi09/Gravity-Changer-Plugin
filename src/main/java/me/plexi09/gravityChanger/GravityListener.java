package me.plexi09.gravityChanger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listens for player movement events to apply custom gravity effects.
 */
public class GravityListener implements Listener {

    private final PlanetManager planetManager;

    public GravityListener(PlanetManager planetManager) {
        this.planetManager = planetManager;
    }

    /**
     * Event handler that customizes gravity when players move.
     * Decreases gravity with altitude (higher altitude -> lower gravity).
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();
        PlanetManager.PlanetProperties props = planetManager.getPlanetProperties(worldName);

        // Calculate custom gravity factor
        double baseGravity = props.getBaseGravity();
        double altitude = player.getLocation().getY();
        double gravityFactor = GravityCalculation.calculateGravity(baseGravity, altitude);

        // Apply vertical velocity manipulation (for demonstration only)
        // This is a simplistic approach; actual gravity manipulation in Minecraft
        // can be more complex and may require repeated tasks or other event hooks.
        if (player.getVelocity().getY() < 0) {
            player.setVelocity(player.getVelocity().multiply(gravityFactor));
        }
    }
}