package com.hackathon.askme.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by ishah on 8/29/15.
 */
@ParseClassName("Category")
public class Category extends ParseObject {
    public int getCategory_Id() {
        return getInt("category_id");
    }

    public void setCategory_Id(int id) {
        put("category_id", id);
    }

    public String getCategory_Name() {
        return getString("category_name");
    }

    public void setCategory_Name(String name) {
        put("category_name", name);
    }

    public static ParseQuery<Category> getQuery() {
        return ParseQuery.getQuery(Category.class);
    }
}
