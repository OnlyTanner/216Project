package test;

import core.Player;
import core.StandardProperty;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlayerTest2 {
    private Player play;
    @BeforeMethod
    public void setUp() {
        System.out.println("starting player testing");
        play = new Player();
    }

    @AfterMethod
    public void tearDown()  {
    }


    @Test
    public void Property()  {
        StandardProperty prop = new StandardProperty("test", "yellow",200,new int[2],400,50, 100);
        StandardProperty prop2 = new StandardProperty("test2", "red",300,new int[2],500,60, 120);



        play.addProperty(prop);
        Assert.assertFalse(play.getProperties().isEmpty());
        play.addProperty(prop2);
        Assert.assertEquals(play.getProperties().get(0),prop);
        Assert.assertEquals(play.getProperties().get(1),prop2);

        Assert.assertEquals(play.getProperties().get(0).getName(),prop.getName());
        Assert.assertEquals(play.getProperties().get(1).getName(),prop2.getName());

        play.removeProperty(prop);
        Assert.assertEquals(play.getProperties().get(0),prop2);
        Assert.assertEquals(play.getProperties().get(0).getName(),prop2.getName());

        play.removeProperty(prop2);
        Assert.assertTrue(play.getProperties().isEmpty());
    }


}