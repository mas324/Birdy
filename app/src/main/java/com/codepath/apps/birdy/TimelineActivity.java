package com.codepath.apps.birdy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.apps.birdy.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends Activity {

    @BindView(R.id.reLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.viewTweet)
    RecyclerView viewTweet;
    private BirdyClient client;
    private BirdyAdapt adapt;
    private List<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        client = BirdyApp.getRestClient(this);
        tweets = new ArrayList<>();
        adapt = new BirdyAdapt(this, tweets);
        viewTweet.setLayoutManager(new LinearLayoutManager(this));
        viewTweet.setAdapter(adapt);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTweets();
            }
        });

        refreshLayout.setRefreshing(true);
        getTweets();
    }

    private void getTweets() {
        client.getTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                adapt.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        tweets.add(Tweet.fromJson(response.getJSONObject(i)));
                        adapt.notifyItemInserted(i);
                    } catch (JSONException e) {
                        Log.e("BirdyListFail", e.getMessage());
                    }
                }
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("BirdyNetworkClient", responseString);
            }
        });
    }
}
