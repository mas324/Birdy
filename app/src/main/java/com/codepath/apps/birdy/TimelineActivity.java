package com.codepath.apps.birdy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.apps.birdy.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private BirdyClient client;
    private RecyclerView viewTweet;
    private BirdyAdapt adapt;
    private List<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = BirdyApp.getRestClient(this);
        viewTweet = findViewById(R.id.viewTweet);
        tweets = new ArrayList<>();
        adapt = new BirdyAdapt(this, tweets);
        viewTweet.setLayoutManager(new LinearLayoutManager(this));
        viewTweet.setAdapter(adapt);

        client.getTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        tweets.add(Tweet.fromJson(response.getJSONObject(i)));
                        adapt.notifyItemInserted(i);
                    } catch (JSONException e) {
                        Log.e("BirdyListFail", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("BirdyNetworkClient", responseString);
            }
        });
    }
}
