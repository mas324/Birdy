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

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirdyAdapt extends RecyclerView.Adapter<BirdyAdapt.ViewHolder> {

    private Context context;
    private List<Tweet> tweets;

    BirdyAdapt(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
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
        @BindView(R.id.profile_pic)
        ImageView profilePic;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tweet)
        TextView tweet;
        @BindView(R.id.handle)
        TextView handle;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
