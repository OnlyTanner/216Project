package test;

import core.Card;
import core.Player;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCard {
    private Card card;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.Card.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.Card.java");
    }

    @BeforeMethod
    public void setUp() throws IOException, FontFormatException {
        card = new Card("test", "desc", "/resources/scripts/go-to-jail.lua");
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGettersAndSetters() {
        Assert.assertEquals(card.getDescription(), "desc");

        try {
            byte[] contents = Files.readAllBytes(Paths.get("src/resources/scripts/go-to-jail.lua"));
            String str = new String(contents, Charset.defaultCharset());
            Assert.assertEquals(card.getScript(), str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(card.getSprite());
    }
}
