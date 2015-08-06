package com.growapp.marvelheroes2;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String LOG_TAG = "LOG_TAG";

    public static final String CONTENT_AUTHORITY = "com.growapp.marvelheroes";
    public static final String PATH_HEROES = "heroes";
    public static final Uri BASE_CONTENT_URI =
            Uri.parse("content://" + CONTENT_AUTHORITY + "/" + PATH_HEROES);

    private DataAdapter adapter;
    private ListView lvHeroes;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d(LOG_TAG, "BASE_CONTENT_URI = " + BASE_CONTENT_URI);

        lvHeroes = (ListView) view.findViewById(R.id.lv_heroes);
        adapter = new DataAdapter(getActivity(), null, 0);
        lvHeroes.setAdapter(adapter);

        //adapter.getCursor().close();

        getLoaderManager().initLoader(0, null, this);

        return view;
    }



    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle arg1) {

        return new CursorLoader(
                getActivity(),
                BASE_CONTENT_URI, //uri для таблицы Classes
                null, //список столбцов, которые должны присутствовать в выборке
                null, // условие WHERE для выборки
                null, // аргументы для условия WHERE
                null); // порядок сортировки
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor newData) {
        Log.d(LOG_TAG, "onLoadFinished");
        String[] colNames = newData.getColumnNames();
        Log.d(LOG_TAG, "newData.getColumnNames() = ");
        for (String s: colNames){
            Log.d(LOG_TAG, "col = " + s);
        }
        adapter.swapCursor(newData);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        //если в полученном результате sql-запроса нет никаких строк,
        //то говорим адаптеру, что список нужно очистить
        Log.d(LOG_TAG, "onLoaderReset");
        adapter.swapCursor(null);
    }

}