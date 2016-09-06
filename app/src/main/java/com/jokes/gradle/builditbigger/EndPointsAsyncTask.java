package com.jokes.gradle.builditbigger;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.example.bplewis5.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by bplewis5 on 9/6/16.
 */
class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.JokeListener, Void, String> {

    private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private JokeListener mJokeListener;
    private static MyApi myApiService = null;


    public interface JokeListener{
        void onJokeReceived(String joke);
    }


    @Override
    protected String doInBackground(JokeListener... jokeListeners) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            mJokeListener = jokeListeners[0];
            myApiService = builder.build();
        }


        try {
            return myApiService.displayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(LOG_TAG, "JOKE RESULT: " + result);
        if (!TextUtils.isEmpty(result)){
            mJokeListener.onJokeReceived(result);
        }
    }
}