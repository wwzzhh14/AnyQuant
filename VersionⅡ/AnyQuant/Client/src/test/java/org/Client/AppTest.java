package org.Client;

import BLTestCase.TestLoginBLService;
import BLTestCase.TestMarketBLService;
import BLTestCase.TestStockBLService;
import NetTestCase.TestLoginNetService;
import NetTestCase.TestMarketNetService;
import NetTestCase.TestStockNetService;
import junit.awtui.TestRunner;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    public static Test suite() {
        TestSuite suite = new TestSuite("Test all interfaces!");
        suite.addTestSuite(TestLoginBLService.class);
        suite.addTestSuite(TestStockBLService.class);
        suite.addTestSuite(TestMarketBLService.class);
        suite.addTestSuite(TestMarketNetService.class);
        suite.addTestSuite(TestStockNetService.class);
        suite.addTestSuite(TestLoginNetService.class);
        return suite;
    }
}
