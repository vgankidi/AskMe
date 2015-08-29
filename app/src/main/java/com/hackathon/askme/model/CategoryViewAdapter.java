package com.hackathon.askme.model;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hackathon.askme.R;
import com.hackathon.askme.model.Category;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ishah on 8/29/15.
 */
public class CategoryViewAdapter extends ArrayAdapter<Category> {

    public CategoryViewAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.discover_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.body = (TextView) convertView.findViewById(R.id.tv_category);
            convertView.setTag(viewHolder);
        }
        final Category category = (Category) getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        holder.body.setText(category.getCategory_Name());

        return convertView;
    }

    final class ViewHolder {
        public TextView body;
    }
}
