package com.codepath.apps.birdy;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.codepath.apps.birdy.models.SampleModel;
import com.codepath.apps.birdy.models.SampleModelDao;

@Database(entities = {SampleModel.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    // Database name to be used
    public static final String NAME = "MyDataBase";

    public abstract SampleModelDao sampleModelDao();
}
