package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TestSprite {
    private TestSprite sprite;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing test.TestSprite.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing test.TestSprite.java");
    }

    @BeforeMethod
    public void setUp() {
        sprite = new TestSprite();
    }

    @AfterMethod
    public void tearDown() {
    }


}