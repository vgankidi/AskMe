package com.hackathon.askme;

import android.app.Activity;
import android.app.ListActivity;
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

import com.hackathon.askme.model.Category;
import com.hackathon.askme.model.CategoryViewAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by ishah on 8/28/15.
 */
public class DiscoverActivity extends AppCompatActivity {
    ListView _listView;
    private List<Category> mCategoryList;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_discover);


    _listView = (ListView) findViewById(R.id.lvDiscover);
    _listView.setTranscriptMode(1);



    // assign the list adapter

   /* Category category1 = new Category();
    category1.setCategory_Id(1);
    category1.setCategory_Name("Business & Entrepreneurship");
    Category category2 = new Category();
    category2.setCategory_Id(2);
    category2.setCategory_Name("Leadership & Skills");
    Category category3 = new Category();
    category3.setCategory_Id(3);
    category3.setCategory_Name("Career");
    Category category4 = new Category();
    category4.setCategory_Id(4);
    category4.setCategory_Name("Workplace");*/
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

        }

    });
    }
}


/*
public class DiscoverActivity extends ListActivity {
    private String context;
    List<Category> categories;
    CategoryViewAdapter categoryViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // storing string resources into Array
      /*  String[] adobe_products = getResources().getStringArray(R.array.categories);

        // Binding resources Array to ListAdapter
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.categories_listview, R.id.label, categories));

        ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String product = ((TextView) view).getText().toString();

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                // sending data to new activity
                i.putExtra("context", "context");
                startActivity(i);

            }
        });

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        context = intent.getStringExtra("context");
        if(context.equals("discover")) {
            ParseQuery<Category> query = ParseQuery.getQuery("Category");
            query.findInBackground(new FindCallback<Category>() {
                public void done(List<Category> categoryList, ParseException e) {
                    if (e == null) {
                        categories.clear();
                        categories.addAll(categoryList);
                        categoryViewAdapter = new CategoryViewAdapter(DiscoverActivity.this, categories);
                        setContentView();
                        //objectsWereRetrievedSuccessfully(objects);
                    } else {
                        //objectRetrievalFailed();
                    }
                }
            }
        } else if(context.equals("answer")) {

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    }
}
*/
