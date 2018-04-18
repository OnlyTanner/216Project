package test;

import core.Sprite;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSprite {
    private Sprite sprite;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing test.TestSprite.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing test.TestSprite.java");
    }

    @BeforeMethod
    public void setUp() throws IOException {
        sprite = new Sprite("/resources/images/logo.png");
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testMiscSettersAndGetters() {
        sprite.setX(5);
        sprite.setY(10);
        Assert.assertEquals(sprite.getX(), 5);
        Assert.assertEquals(sprite.getY(), 10);
        Assert.assertEquals(sprite.getWidth(), 471);
        Assert.assertEquals(sprite.getHeight(), 144);
    }

    @Test
    public void testSpriteManipulation() {
        sprite.rotate(Math.PI / 2.0); //rotate 90 degrees
        Assert.assertEquals(sprite.getWidth(), 144);
        Assert.assertEquals(sprite.getHeight(), 471);
        sprite.scale(2); //double image size
        Assert.assertEquals(sprite.getWidth(), 288);
        Assert.assertEquals(sprite.getHeight(), 942);
        sprite.reset(); //reset to original
        Assert.assertEquals(sprite.getWidth(), 471);
        Assert.assertEquals(sprite.getHeight(), 144);
    }
}