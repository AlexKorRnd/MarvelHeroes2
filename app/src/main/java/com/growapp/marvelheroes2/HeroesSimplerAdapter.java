package com.growapp.marvelheroes2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Алексей on 06.08.2015.
 */
public class HeroesSimplerAdapter extends SimpleCursorAdapter {

    private final Context mContext;
    private final Cursor mCursor;

    public HeroesSimplerAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        mContext = context;
        mCursor = c;
    }

    @Override
    public void setViewText(TextView v, String text) {
        v.setText(text);
    }

    @Override
    public Cursor swapCursor(Cursor c) {
        return super.swapCursor(c);
    }
}
