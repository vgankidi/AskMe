package com.hackathon.askme.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by ishah on 8/29/15.
 */
@ParseClassName("Category")
public class Category extends ParseObject {
    public String getId() {
        return getString("id");
    }

    public void setId(String id) {
        put("id", id);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public static ParseQuery<Category> getQuery() {
        return ParseQuery.getQuery(Category.class);
    }
}
