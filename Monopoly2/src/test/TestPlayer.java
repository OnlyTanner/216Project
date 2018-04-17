package test;

import core.Player;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
    public void setUp() {
        player1 = new Player();
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
            assertEquals(tempPos, player1.getPlayerPos()); //ensure the value didn't change
        } catch(Exception e) {
            Assert.fail("Unexpected exception.", e);
        }

        tempPos = player1.getPlayerPos();
        try {
            player1.setPlayerPos(40);
            assertEquals(tempPos, player1.getPlayerPos()); //ensure the value didn't change
        } catch(Exception e) {
            Assert.fail("Unexpected exception.", e);
        }
    }

    @Test
    public void testGiveMoney() {
        int money = player1.get_money() + 500;
        player1.giveMoney(500);
        assertEquals(money, player1.get_money());

        money = player1.get_money();
        try {
            player1.giveMoney(-1);
            assertEquals(money, player1.get_money());
        } catch (Exception e) {
            Assert.fail("Unexpected exception.", e);
        }
    }

    @Test
    public void testTakeMoney() {
        int money = player1.get_money() - 500;
        player1.takeMoney(500);
        assertEquals(money, player1.get_money());

        money = player1.get_money();
        try {
            player1.takeMoney(-1);
            assertEquals(money, player1.get_money());
        } catch (Exception e) {
            Assert.fail("Unexpected exception.", e);
        }

        money = player1.get_money();
        try {
            Assert.assertTrue(player1.getStillInGame());
            player1.takeMoney(money + 1);
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
            assertEquals(turns, player1.getTurnsLeftInJail());
        } catch (Exception e) {
            
        }
    }
}