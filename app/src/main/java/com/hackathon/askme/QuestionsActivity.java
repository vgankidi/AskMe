
package com.hackathon.askme;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;

        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.widget.ExpandableListView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

/**
 * Created by vigankid on 8/29/15.
 */
public class QuestionsActivity extends Activity {
    private static final String TAG = "QuestionsActivity";

    @InjectView(R.id.quote)
    TextView _nameText;

    private ArrayList<String> parentItems;
    private HashMap<String, List<String>> childItems;
    ExpandableListView Exp_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        ButterKnife.inject(this);

        parentItems = new ArrayList<String>();
        childItems = new HashMap<String, List<String>>();
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);

        Exp_list.setDividerHeight(2);
        Exp_list.setGroupIndicator(null);
        Exp_list.setClickable(true);

        // Set the Items of Parent
        setGroupParents();
        // Set The Child Data
        setChildData();

        // Create the Adapter


        ExpandableListAdapter exp_adp = new ExpandableListAdapter(this, parentItems, childItems);

        // Set the Adapter to expandableList
        Exp_list.setAdapter(exp_adp);
      //  Exp_list.setOnChildClickListener(this);


    }


    public void setGroupParents()
    {
        parentItems.add("Fruits");
        parentItems.add("Flowers");
        parentItems.add("Animals");
        parentItems.add("Birds");
    }
    public void setChildData()
    {

        // Add Child Items for Fruits
        ArrayList<String> child = new ArrayList<String>();
        child.add("Apple");
        child.add("Mango");
        child.add("Banana");
        child.add("Orange");

        childItems.put(parentItems.get(0), child);

        // Add Child Items for Flowers
        child = new ArrayList<String>();
        child.add("Rose");
        child.add("Lotus");
        child.add("Jasmine");
        child.add("Lily");

        childItems.put(parentItems.get(1),child);

        // Add Child Items for Animals
        child = new ArrayList<String>();
        child.add("Lion");
        child.add("Tiger");
        child.add("Horse");
        child.add("Elephant");

        childItems.put(parentItems.get(2),child);

        // Add Child Items for Birds
        child = new ArrayList<String>();
        child.add("Parrot");
        child.add("Sparrow");
        child.add("Peacock");
        child.add("Pigeon");

        childItems.put(parentItems.get(3),child);
    }

}
