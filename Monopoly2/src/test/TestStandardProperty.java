package test;

import core.Player;
import core.StandardProperty;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestStandardProperty {
    private StandardProperty prop;
    private int[] test;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.StandardProperty.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.StandardProperty.java");
    }

    @BeforeMethod
    public void setUp() {
        test = new int[1];
        test[0] = 15;
        prop = new core.StandardProperty("test_prop", "blue", 100, test, 500, 100, 300);
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGettersAndSetters() {
        prop.setMortgaged(true);
        Assert.assertTrue(prop.getMortgaged());
        Assert.assertEquals(prop.getColor(), "blue");
        Assert.assertEquals(prop.getName(), "test_prop");
        Assert.assertEquals(prop.getCost(), 100);
        Assert.assertEquals(prop.getMortgageRate(), 500);
        Assert.assertEquals(prop.getHouseCost(), 100);
    }

    @Test
    public void testRent() {
        try {
            Assert.assertEquals(prop.getRent(new Player(1), 4), 15);
        } catch (IOException e) {
            Assert.fail("Unexpected IOException.", e);
        }
    }

    @Test
    public void testUpgradeAndDowngrade() {
        Assert.assertEquals(prop.getNumUpgrades(), 0);
        prop.upgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 1);
        prop.upgrade();
        prop.upgrade();
        prop.upgrade();
        prop.upgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 5);
        prop.upgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 5);
        prop.downgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 4);
        prop.downgrade();
        prop.downgrade();
        prop.downgrade();
        prop.downgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 0);
        prop.downgrade();
        Assert.assertEquals(prop.getNumUpgrades(), 0);
    }
}
