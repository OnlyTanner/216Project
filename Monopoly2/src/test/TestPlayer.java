package test;

import core.Player;
import core.Property;
import core.StandardProperty;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestPlayer {
    private Player player1;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.Player.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.Player.java");
    }

    @BeforeMethod
    public void setUp() throws IOException {
        player1 = new Player(1);
    }

    @AfterMethod
    public void tearDown() {
    }

    /**
     * Should only allow the playerPos variable to be set between 0 and 39 as that is how many spaces are on the board
     */
    @Test
    public void testSetPlayerPos() {
        try {
            player1.setPlayerPos(0);
            assertEquals(player1.getPlayerPos(), 0);
            player1.setPlayerPos(1);
            assertEquals(player1.getPlayerPos(), 1);
            player1.setPlayerPos(38);
            assertEquals(player1.getPlayerPos(), 38);
            player1.setPlayerPos(39);
            assertEquals(player1.getPlayerPos(), 39);
        } catch (Exception e) {
            Assert.fail("A valid position caused an exceptional case.", e);
        }

        int tempPos = player1.getPlayerPos();
        try {
            player1.setPlayerPos(-1);
            assertEquals(player1.getPlayerPos(), tempPos); //ensure the value didn't change
        } catch(Exception e) {
            Assert.fail("Unexpected exception.", e);
        }

        tempPos = player1.getPlayerPos();
        try {
            player1.setPlayerPos(40);
            assertEquals(player1.getPlayerPos(), tempPos); //ensure the value didn't change
        } catch(Exception e) {
            Assert.fail("Unexpected exception.", e);
        }
    }

    @Test
    public void testGiveMoney() {
        int money = player1.get_money() + 500;
        player1.giveMoney(500);
        assertEquals(player1.get_money(), money);

        money = player1.get_money();
        try {
            player1.giveMoney(-1);
            assertEquals(player1.get_money(), money);
        } catch (Exception e) {
            Assert.fail("Unexpected exception.", e);
        }
    }

    @Test
    public void testTakeMoney() {
        int money = player1.get_money() - 500;
        player1.takeMoney(500);
        assertEquals(player1.get_money(), money);

        money = player1.get_money();
        try {
            player1.takeMoney(-1);
            assertEquals(player1.get_money(), money);
        } catch (Exception e) {
            Assert.fail("Unexpected exception.", e);
        }

        money = player1.get_money();
        try {
            Assert.assertTrue(player1.getStillInGame());
            player1.takeMoney(money + 1);
            Assert.assertFalse(player1.getStillInGame());
            player1.takeMoney(1); //take money after the player has no money
            Assert.assertFalse(player1.getStillInGame());
        } catch (Exception e) {
            Assert.fail("Unexpected exception.", e);
        }
    }

    @Test
    public void testSetTurnsLeftInJail() {
        player1.setTurnsLeftInJail(1);
        assertEquals(player1.getTurnsLeftInJail(), 1);

        int turns = player1.getTurnsLeftInJail();
        try {
            player1.setTurnsLeftInJail(-1);
            assertEquals(player1.getTurnsLeftInJail(), turns);
        } catch (Exception e) {
            
        }
    }

    @Test
    public void testMiscSettersAndGetters() {
        player1.setPlayerPos(5);
        player1.set_money(1500);
        player1.setTurnsLeftInJail(3);
        player1.setGetOutOfJailFreeCardCnt(2);
        player1.setStillInGame(false);
        assertEquals(player1.getPlayerPos(), 5);
        assertEquals(player1.get_money(), 1500);
        assertEquals(player1.getTurnsLeftInJail(), 3);
        assertEquals(player1.getGetOutOfJailFreeCards(), 2);
        assertEquals(player1.getStillInGame(), false);
        assertNotNull(player1.getSprite());

        player1.addPlayerPos(3);
        assertEquals(player1.getPlayerPos(), 8);
    }

    @Test
    public void testTurnsLeftInJail() {
        player1.setTurnsLeftInJail(5);
        assertEquals(player1.getTurnsLeftInJail(), 5);
        player1.setTurnsLeftInJail(-1); //invalid value
        assertEquals(player1.getTurnsLeftInJail(), 5); //shouldn't change
    }

    @Test
    public void testProperties() {
        StandardProperty p = new StandardProperty("test", "blue", 50, new int[5], 10, 100, 500);
        player1.addProperty(p);
        ArrayList<Property> properties = player1.getProperties();
        assertEquals(properties.size(), 1);
        assertEquals(properties.get(0), p);
    }
}