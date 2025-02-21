package me.plexi09.gravityChanger;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages planet (world) properties like base gravity
 * and whether or not the planet has an atmosphere.
 */
public class PlanetManager {

    private final Map<String, PlanetProperties> planetPropertiesMap = new HashMap<>();

    public PlanetManager(FileConfiguration config) {
        // Read planets from config and populate planetPropertiesMap
        for (String planetName : config.getConfigurationSection("planets").getKeys(false)) {
            double gravity = config.getDouble("planets." + planetName + ".gravity", 1.0);
            boolean hasAtmosphere = config.getBoolean("planets." + planetName + ".hasAtmosphere", false);
            planetPropertiesMap.put(planetName, new PlanetProperties(gravity, hasAtmosphere));
        }
    }

    /**
     * Retrieve planet properties by world name.
     * Defaults to Earth-like properties if not defined in the config.
     */
    public PlanetProperties getPlanetProperties(String worldName) {
        return planetPropertiesMap.getOrDefault(worldName, new PlanetProperties(1.0, true));
    }

    /**
     * Object to store each planet's base gravity and atmosphere state.
     */
    public static class PlanetProperties {
        private final double baseGravity;
        private final boolean hasAtmosphere;

        public PlanetProperties(double baseGravity, boolean hasAtmosphere) {
            this.baseGravity = baseGravity;
            this.hasAtmosphere = hasAtmosphere;
        }

        public double getBaseGravity() {
            return baseGravity;
        }

        public boolean hasAtmosphere() {
            return hasAtmosphere;
        }
    }
}