package test;

import core.StandardProperty;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class TestStandardProperty {
    private StandardProperty prop;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Started testing core.StandardProperty.java");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Finished testing core.StandardProperty.java");
    }

    @BeforeMethod
    public void setUp() {
        //prop = new core.StandardProperty();
    }

    @AfterMethod
    public void tearDown() {
    }


}
