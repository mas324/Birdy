package com.codepath.apps.birdy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.birdy.models.SampleModel;
import com.codepath.apps.birdy.models.SampleModelDao;
import com.codepath.oauth.OAuthLoginActionBarActivity;

public class LoginActivity extends OAuthLoginActionBarActivity<BirdyClient> {

    SampleModelDao sampleModelDao;

    AsyncTask<SampleModel, Void, Void> task = new AsyncTask<SampleModel, Void, Void>() {
        @Override
        protected Void doInBackground(SampleModel... sampleModels) {
            sampleModelDao.insertModel(sampleModels);
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SampleModel sampleModel = new SampleModel();
        sampleModel.setName("CodePath");

        sampleModelDao = ((BirdyApp) getApplicationContext()).getMyDatabase().sampleModelDao();

        task.execute(sampleModel);
    }


    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    // OAuth authenticated successfully, launch primary authenticated activity
    // i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(this, TimelineActivity.class);
        startActivityForResult(i, 50);
    }

    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }

    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
        getClient().connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 50) {
            Toast.makeText(getApplicationContext(), "Exiting app", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("BirdyResult", "How did you get into here?");
        }
    }
}
