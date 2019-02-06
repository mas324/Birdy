package com.codepath.apps.birdy;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.facebook.stetho.Stetho;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     BirdyClient client = BirdyApp.getRestClient(Context context);
 *     // use client to send requests to API
 *
 */
public class BirdyApp extends Application {

    TweetBase myDatabase;

    public static BirdyClient getRestClient(Context context) {
        return (BirdyClient) BirdyClient.getInstance(BirdyClient.class, context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // when upgrading versions, kill the original tables by using
        // fallbackToDestructiveMigration()
        myDatabase = Room.databaseBuilder(this, TweetBase.class,
                TweetBase.NAME).fallbackToDestructiveMigration().build();

        // use chrome://inspect to inspect your SQL database
        Stetho.initializeWithDefaults(this);
    }

    public TweetBase getMyDatabase() {
        return myDatabase;
    }
}