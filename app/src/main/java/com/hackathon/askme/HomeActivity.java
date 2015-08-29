package com.hackathon.askme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hackathon.askme.controller.ChatActivity;
import com.hackathon.askme.model.Message;
import com.parse.Parse;
import com.parse.ParseObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vigankid on 8/29/15.
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @InjectView(R.id.quote) TextView _nameText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "erM6HVhGfEc0AS8LKygVeG26ry5L6mcgfqQvaewl", "TBM5x1u8zIwTQWJRcq1raRAn9IK74sogDuKYGO1I");
        ParseObject.registerSubclass(Message.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_ask:
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
