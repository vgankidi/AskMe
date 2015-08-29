package com.hackathon.askme.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hackathon.askme.R;
import com.hackathon.askme.model.Message;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends Activity {
    private static final String TAG = ChatActivity.class.getName();
    private static String sUserId;
//    public static final String USER_ID_KEY = "userId";
    private EditText etMessage;
    private Button btSend;

    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private ChatListAdapter mAdapter;
    // Keep track of initial load to scroll to the bottom of the ListView
    private boolean mFirstLoad;

    private static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;

    private static final Map<Integer, List<String>> acceptableAnswers = new HashMap<Integer, List<String>>() {
        {
            put(0, new ArrayList<String>() {
                {
                    add("Hi. I am Nessa. How are you doing");
                    add("Hello. I am Nessa.");
                }
            });
            put(1, new ArrayList<String>() {
                {
                    add("Good to hear that.");
                    add("Great! How can I help you today?");
                }
            });
            put(2, new ArrayList<String>() {
                {
                    add("I am sorry to hear that.");
                }
            });
        }
    };
    private static final Map<String, Integer> qAndA = new HashMap<String, Integer>() {
        {
            put("hi", 0);
            put("hello", 0);
            put("i am fine", 1);
            put("i am doing good", 1);
            put("great", 1);
            put("bad", 2);
            put("not doing great", 2);
            put("i have had better days", 2);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // User login
        if (ParseUser.getCurrentUser() != null) {
            startWithCurrentUser();
        } else {
            login();
        }
    }

    private void startWithCurrentUser() {
        sUserId = ParseUser.getCurrentUser().getObjectId();
        setupMessagePosting();
    }

    // Create an anonymous user using ParseAnonymousUtils and set sUserId
    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Anonymous login failed: " + e.toString());
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }

    private void setupMessagePosting() {
        // Find the text field and button
        etMessage = (EditText) findViewById(R.id.etMessage);
        btSend = (Button) findViewById(R.id.btSend);
        lvChat = (ListView) findViewById(R.id.lvChat);
        mMessages = new ArrayList<Message>();
        //Automatically scroll to the bottom when a data set change notification is received and
        // only if the last is already visible on screen. Don't scroll to the bottom otherwise.
        lvChat.setTranscriptMode(1);
        mFirstLoad = true;
        mAdapter = new ChatListAdapter(ChatActivity.this, sUserId, mMessages);
        lvChat.setAdapter(mAdapter);

        // When send button is clicked, create message object on Parse
        btSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = etMessage.getText().toString();
                // Use Message model to create new messages now
                final Message message = new Message();
                message.setUserId(sUserId);
                message.setBody(data);
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        String response = processMessage(message);
                        //receiveMessages();
                    }
                });
                etMessage.setText("");
            }
        });
    }

    private String processMessage(Message message) {
        String userResponse = message.getBody().toLowerCase();
        if (qAndA.containsKey(userResponse)) {
            List<String> responses = acceptableAnswers.get(qAndA.get(userResponse));
            return null;
        } else {
            return "I am sorry. I am not able to understand what you just said.";
        }
    }

    // Query messages from Parse so we can load them into the chat adapter
    private void receiveMessages() {
        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        query.orderByAscending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    mMessages.clear();
                    mMessages.addAll(messages);
                    mAdapter.notifyDataSetChanged(); // update adapter
                    // Scroll to the bottom of the list on initial load
                    if(mFirstLoad) {
                        lvChat.setSelection(mAdapter.getCount() - 1);
                        mFirstLoad = false;
                    }
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }

}
