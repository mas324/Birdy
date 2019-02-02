package com.codepath.apps.birdy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.birdy.models.Tweet;

import java.util.List;

public class BirdyAdapt extends RecyclerView.Adapter<BirdyAdapt.ViewHolder> {

    private Context context;
    private List<Tweet> tweets;

    public BirdyAdapt(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.tweet_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Tweet tweet = tweets.get(i);
        viewHolder.name.setText(tweet.getUser().getName());
        viewHolder.handle.setText(tweet.getUser().getHandle());
        Glide.with(context).load(tweet.getUser().getProfilePic()).into(viewHolder.profilePic);
        viewHolder.tweet.setText(tweet.getTweet());
        viewHolder.time.setText(tweet.getCreatedTime());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView profilePic;
        private TextView name;
        private TextView tweet;
        private TextView handle;
        private TextView time;

        ViewHolder(View view) {
            super(view);
            profilePic = view.findViewById(R.id.profile_pic);
            name = view.findViewById(R.id.name);
            tweet = view.findViewById(R.id.tweet);
            handle = view.findViewById(R.id.handle);
            time = view.findViewById(R.id.time);
        }
    }
}
