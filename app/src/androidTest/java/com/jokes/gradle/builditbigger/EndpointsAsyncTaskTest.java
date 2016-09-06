package com.jokes.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.text.TextUtils;

import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by bplewis5 on 9/6/16.
 */

public class EndpointsAsyncTaskTest extends AndroidTestCase{

    public void testEndpointsAsyncTask() throws Exception {

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.execute(new MainActivityFragment().getActivity());
        String result = asyncTask.get(30, TimeUnit.SECONDS);
        Assert.assertTrue(!TextUtils.isEmpty(result));
    }
}
