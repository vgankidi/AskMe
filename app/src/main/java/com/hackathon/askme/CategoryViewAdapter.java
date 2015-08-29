package com.hackathon.askme;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.hackathon.askme.models.Category;

import java.util.List;

/**
 * Created by ishah on 8/29/15.
 */
public class CategoryViewAdapter extends ArrayAdapter<Category> {

    public CategoryViewAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
    }

    @Override
    public View getView(int position, ) {

    }
}
