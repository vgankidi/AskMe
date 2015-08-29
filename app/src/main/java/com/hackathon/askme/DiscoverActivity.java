package com.hackathon.askme;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hackathon.askme.model.Category;
import com.hackathon.askme.model.CategoryViewAdapter;

import java.util.List;

/**
 * Created by ishah on 8/28/15.
 */
public class DiscoverActivity extends ListActivity {
    private String context;
    List<Category> categories;
    CategoryViewAdapter categoryViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // storing string resources into Array
        String[] adobe_products = getResources().getStringArray(R.array.categories);

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
        /*
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

        } */
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    }
}
