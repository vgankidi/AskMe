package com.hackathon.askme;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vigankid on 8/29/15.
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @InjectView(R.id.quote) TextView _nameText;
    @InjectView(R.id.action_ask) Button _askButton;
    @InjectView(R.id.action_answer) Button _answerButton;
    @InjectView(R.id.action_discover) Button _discoverButton;
    @InjectView(R.id.action_notifications) Button _notificationsButton;
    @InjectView(R.id.action_me) Button _meButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.inject(this);

        _discoverButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(HomeActivity.this, DiscoverActivity.class);
                intent.putExtra("context", "discover");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
