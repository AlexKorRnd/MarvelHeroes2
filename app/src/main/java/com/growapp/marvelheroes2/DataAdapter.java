package com.growapp.marvelheroes2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Алексей on 06.08.2015.
 */
public class DataAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    private static final String COLUMN_NAME = "name";

    public DataAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context ctx, Cursor cur, ViewGroup parent) {
        View root = mInflater.inflate(R.layout.list_item, parent, false);

        ViewHolder holder = new ViewHolder();
        TextView tvName = (TextView) root.findViewById(R.id.tv_list_item);

        holder.tvName = tvName;
        root.setTag(holder);

        return root;
    }

    @Override
    public void bindView(View view, Context ctx, Cursor cur) {

        String name = cur.getString(cur.getColumnIndex(COLUMN_NAME));

        ViewHolder holder = (ViewHolder) view.getTag();

        if(holder != null) {
            holder.tvName.setText(name);
        }
    }


    public static class ViewHolder {
        public TextView tvName;
    }
}
