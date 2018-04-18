package test;

import core.OneOpSpace;
import core.Player;
import core.Sprite;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class OneOpSpaceTest {
    private OneOpSpace op;

    @BeforeMethod
    public void setUp() throws IOException {
        System.out.println("Advance to Go");
        op = new OneOpSpace("test", "/resources/scripts/advance-to-go.lua");
    }

    @AfterMethod
    public void tearDown()  {
    }

    @Test
    public void testGeterandSetters() {
        String name = "test";
        Assert.assertEquals(name,op.getName());



        try{
            Sprite sprite = new Sprite("/resources/images/card.png");
            Assert.assertNull(op.getSprite());
            op.setSprite(sprite);
            Assert.assertEquals(op.getSprite(),sprite);

            Assert.assertEquals(sprite.getWidth(), op.getSprite().getWidth());
            Assert.assertEquals(sprite.getHeight(),op.getSprite().getHeight());

        }catch(IOException e){
            System.out.println("IOException " + e + " occurred");

        }

    }

}