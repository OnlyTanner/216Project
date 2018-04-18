package test;

import core.Die;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class TestDie {
    private Die die;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.StandardProperty.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.StandardProperty.java");
    }

    @BeforeMethod
    public void setUp() throws IOException {
        die = new Die();
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testGettersAndSetters() {
        die.setX(5);
        die.setY(10);
        Assert.assertEquals(die.getX(), 5);
        Assert.assertEquals(die.getY(), 10);
        Assert.assertEquals(die.getWidth(), 86);
        Assert.assertEquals(die.getHeight(), 86);
    }

    @Test
    public void testSetNum() {
        try { //valid inputs
            die.setNum(1);
            die.setNum(6);
        } catch (Exception e) {
            Assert.fail("Unexpected Exception.", e);
        }

        //invalid inputs
        try {
            die.setNum(0);
        } catch (RuntimeException e) { }
        try {
            die.setNum(-1);
        } catch (RuntimeException e) { }
        try {
            die.setNum(7);
        } catch (RuntimeException e) { }
    }
}
