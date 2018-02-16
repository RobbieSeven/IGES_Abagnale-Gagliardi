package com.giordanogiammaria.microapp30.facade;

import com.giordanogiammaria.microapp30.manage_file.ManageFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Giordano Giammaria on 12/02/2018.
 */
public class ManageFileTest {

    private ManageFile manageFile= null;
    @Before
    public void setUp() throws Exception {
        manageFile=new ManageFile();
        assertNotNull(manageFile.createDir(new File("test")));
    }

    @Test
    public void testLaunch(){

    }
    @After
    public void tearDown() throws Exception {
        manageFile=null;
    }

}