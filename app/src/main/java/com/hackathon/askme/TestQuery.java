package com.hackathon.askme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.hackathon.askme.model.Category;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishah on 8/29/15.
 */
public class TestQuery extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testquery);
        final TextView text = (TextView) findViewById(R.id.queryTest);
        final List<String> categoriesList = new ArrayList<>();
        ParseQuery query = new ParseQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
           @Override
            public void done(List<ParseObject> categories, ParseException e) {
                if (e == null) {
                    Log.d("categories", "Retrieved " + categories.size() + " categories");
                    for(ParseObject category: categories) {
                       String name = category.getString("category_name");
                       categoriesList.add(name);
                    }
                    text.setText(categoriesList.toString());
                } else {
                    Log.d("categories", "Error: " + e.getMessage());
                }
            }
        });
    }
}
