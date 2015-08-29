package com.hackathon.askme.controller;

import android.app.Application;

import com.hackathon.askme.model.Category;
import com.hackathon.askme.model.Message;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by vigankid on 8/29/15.
 */
public class ChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "erM6HVhGfEc0AS8LKygVeG26ry5L6mcgfqQvaewl", "TBM5x1u8zIwTQWJRcq1raRAn9IK74sogDuKYGO1I");
        ParseObject.registerSubclass(Message.class);
        ParseObject.registerSubclass(Category.class);
    }

}
