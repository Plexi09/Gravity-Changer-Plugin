package com.example.gravityplugin;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the GravityPlugin, managing initialization and integration with Multiverse-Core.
 * This plugin modifies gravity per world (planet) and decreases it with altitude.
 */
public class GravityPlugin extends JavaPlugin {

    private static GravityPlugin instance;
    private MultiverseCore multiverseCore;
    private PlanetManager planetManager;

    @Override
    public void onEnable() {
        instance = this;

        // Check if Multiverse-Core is enabled
        if (Bukkit.getPluginManager().getPlugin("Multiverse-Core") instanceof MultiverseCore) {
            this.multiverseCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        } else {
            getLogger().severe("Multiverse-Core is not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Load default config if none exists
        saveDefaultConfig();

        // Initialize PlanetManager with config
        planetManager = new PlanetManager(getConfig());

        // Register gravity event listener
        getServer().getPluginManager().registerEvents(new GravityListener(planetManager), this);

        getLogger().info("GravityPlugin has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("GravityPlugin has been disabled.");
    }

    public static GravityPlugin getInstance() {
        return instance;
    }

    public MultiverseCore getMultiverseCore() {
        return multiverseCore;
    }

    public PlanetManager getPlanetManager() {
        return planetManager;
    }
}