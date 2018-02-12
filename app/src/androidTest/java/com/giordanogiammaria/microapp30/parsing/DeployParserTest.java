package com.giordanogiammaria.microapp30.parsing;

import com.giordanogiammaria.microapp30.MicroAppActivity;
import com.giordanogiammaria.microapp30.facade.Facade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;


/**
 * Created by Giordano Giammaria on 12/02/2018.
 */
public class DeployParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLaunch() throws FileNotFoundException {
        MicroAppActivity microAppActivity= new MicroAppActivity();
        Facade facade=new Facade(microAppActivity.getApplicationContext());
        String path=facade.getLocalPath();
        DeployParser deployParser = new DeployParser(path+"file3.xml");


    }
    @After
    public void tearDown() throws Exception {
    }

}