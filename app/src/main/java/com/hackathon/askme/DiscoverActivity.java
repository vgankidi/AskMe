package com.hackathon.askme;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathon.askme.controller.ChatActivity;
import com.hackathon.askme.model.Category;
import com.hackathon.askme.model.CategoryViewAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;


/**
 * Created by ishah on 8/28/15.
 */
public class DiscoverActivity extends AppCompatActivity {
    ListView _listView;
    String context;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        Intent intent = getIntent();
        context = intent.getStringExtra("context");

        _listView = (ListView) findViewById(R.id.lvDiscover);
        _listView.setTranscriptMode(1);

        ParseQuery<Category> query = ParseQuery.getQuery(Category.class);
        query.findInBackground(new FindCallback<Category>() {
            @Override
            public void done(List<Category> categories, ParseException e) {
                if (e == null) {
                    Log.d("categories", "Retrieved " + categories.size() + " categories");
                    CategoryViewAdapter myAdapter = new CategoryViewAdapter(getApplicationContext(), categories);
                    _listView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                } else {
                    Log.d("categories", "Error: " + e.getMessage());
                }
            }
        });

        // Define onItemClick behavior
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                Category itemValue = (Category) _listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue.getCategory_Name(), Toast.LENGTH_LONG)
                        .show();

                // TODO Call QuestionActivity
//                if(context.equals("answer")) {
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    intent.putExtra("action", "answer");
//                    intent.putExtra("category", (java.io.Serializable) itemValue);
//                    startActivity(intent);
//                } else if (context.equals("discover")) {
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    intent.putExtra("action", "discover");
//                    intent.putExtra("category", (java.io.Serializable) itemValue);
//                    startActivity(intent);
//                }
            }
        });
    }
}

