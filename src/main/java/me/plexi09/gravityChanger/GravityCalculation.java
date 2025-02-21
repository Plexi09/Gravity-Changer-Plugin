package me.plexi09.gravityChanger;

/**
 * Responsible for calculating gravity based on base gravity and altitude.
 */
public class GravityCalculation {

    private static final double ALTITUDE_SCALING_FACTOR = 0.002; // Adjust to increase or decrease the effect

    /**
     * Example gravity calculation that reduces gravity with altitude.
     * @param baseGravity the base gravity for the planet
     * @param altitude the current altitude of the player
     * @return a gravity multiplier to be applied
     */
    public static double calculateGravity(double baseGravity, double altitude) {
        // Gravity decreases linearly with altitude
        double decreasedGravity = baseGravity - (altitude * ALTITUDE_SCALING_FACTOR);

        // Ensure gravity does not drop below some minimum value
        double minGravity = 0.1;
        return Math.max(decreasedGravity, minGravity);
    }
}