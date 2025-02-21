package me.plexi09.gravityChanger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Simple test cases to verify the gravity calculation logic.
 * Note: This is a plain JUnit test. For in-game behavior, more
 * complex integration tests or in-game tests would be needed.
 */
public class GravityPluginTest {

    @Test
    public void testGravityCalculation() {
        double baseGravity = 0.5;
        double altitude1 = 60;
        double altitude2 = 200;

        double gravity1 = GravityCalculation.calculateGravity(baseGravity, altitude1);
        double gravity2 = GravityCalculation.calculateGravity(baseGravity, altitude2);

        // Check that gravity at higher altitude is lower, but not below the minimum
        Assert.assertTrue(gravity1 >= 0.1);
        Assert.assertTrue(gravity2 >= 0.1);
        Assert.assertTrue(gravity1 > gravity2);
    }
}