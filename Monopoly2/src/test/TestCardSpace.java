package test;

import core.CardSpace;
import org.json.JSONException;
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
        //space = new core.CardSpace();
    }

    @AfterMethod
    public void tearDown() {
    }


}
