package test;

import core.CardSpace;
import core.Sprite;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

@Test
public class TestCardSpace {
    private CardSpace space;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.CardSpace.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.CardSpace.java");
    }

    @BeforeMethod
    public void setUp() throws IOException, JSONException, FontFormatException {
        space = new core.CardSpace("test", "/resources/config/chance.json");
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testDrawCard(){
        Assert.assertNotNull(space.drawCard());
    }

    @Test
    public void testCardSprite() {
        try {
            space.setSprite(new Sprite("/resources/images/card.png"));
            Assert.assertEquals(space.getSprite().getWidth(), new Sprite("/resources/images/card.png").getWidth());
            Assert.assertEquals(space.getSprite().getHeight(), new Sprite("/resources/images/card.png").getHeight());
        } catch(IOException e) {
            Assert.fail("Unexpected IOException.", e);
        }
    }

    @Test
    public void testGetName() {
        //The deck should be named "test"
        Assert.assertEquals(space.getName(), "test");
    }

    @Test
    public void testCardCnt() {
        //The chance deck should have 17 cards
        Assert.assertEquals(space.getCardCnt(), 17);
    }
}
