package com.vokal.test.exp;

import android.test.ActivityInstrumentationTestCase2;

import com.vokal.test.exp.network.*;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.vokal.test.exp.UserListActivityTest \
 * com.vokal.test.exp.tests/android.test.InstrumentationTestRunner
 */
public class UserListActivityTest extends ActivityInstrumentationTestCase2<UserListActivity> {

    public UserListActivityTest() {
        super("com.vokal.test.exp", UserListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        NetworkClient.getInstance().setImpl(new TestNetworkImpl());       
        getActivity();
    }

    public void testSleep() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }

}
