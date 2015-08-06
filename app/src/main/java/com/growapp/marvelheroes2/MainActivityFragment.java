package com.growapp.marvelheroes2;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String LOG_TAG = "LOG_TAG";

    public static final String CONTENT_AUTHORITY = "com.growapp.marvelheroes";
    public static final String PATH_HEROES = "heroes";

    public static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + CONTENT_AUTHORITY + "/" + PATH_HEROES);

    private ArrayAdapter<String> mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d(LOG_TAG, "BASE_CONTENT_URI = " + BASE_CONTENT_URI);

        /*CursorLoader cursorLoader =
                new CursorLoader(getActivity(), BASE_CONTENT_URI, null, null, null, null);


*/
        Cursor cursor = getActivity().
                getContentResolver().query(BASE_CONTENT_URI, null, null, null, null);

        cursor.moveToFirst();

        Log.d(LOG_TAG, "cursor.getCount() = " + cursor.getCount());

        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.tv_list_item);

        ListView listView = (ListView) view.findViewById(R.id.lv_heroes);
        listView.setAdapter(mAdapter);

        for (int i=0; i<cursor.getCount(); ++i){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Log.d(LOG_TAG, "name = " + name);
            mAdapter.add(name);

            cursor.moveToNext();
        }

        return view;
    }
}
