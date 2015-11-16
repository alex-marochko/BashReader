package name.marochko.bashreader;

/**
 * Simple app to parse html from bash.im site
 * (it contains funny quotes at russian language).
*/

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BashMainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "marinfo";
    private final static String PREF_ID = "BASHREADER_PREF_ID";
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;



//    Simplest interface: list and button to (re)load quotes from site.
    ListView listViewQuotes;
    Button buttonLoad;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bash_main);

        listViewQuotes = (ListView)findViewById(R.id.listViewQuotes);
        buttonLoad = (Button)findViewById(R.id.buttonLoad);

    }

    public void onButtonLoadClick(View v){

//        creating and starting an object of the AsyncTask-based parsing class
        new BashParser(listViewQuotes, this).execute();
    }

    @Override
    protected void onResume(){
        super.onResume();

        preferences = getPreferences(MODE_PRIVATE);



        Gson gson = new Gson();
        String jsonString;

        jsonString = preferences.getString("list", "");

        Type type = new TypeToken<List<String>>(){}.getType();

        List<String> stringList = gson.fromJson(jsonString, type);

        Log.i(LOG_TAG, "stringList is ready");


        if(stringList != null){
            String[] array = stringList.toArray(new String[1]);
            ArrayAdapter<String> adapter;

            adapter = new ArrayAdapter<>(this, R.layout.quotes_list_item, array);

            listViewQuotes.setAdapter(adapter);

        }



    }


    @Override
    protected void onPause(){
        super.onPause();

        preferencesEditor = preferences.edit();

        Gson gson = new Gson();

        String[] array = getStringArray(listViewQuotes.getAdapter());

        String jsonString = gson.toJson(array);

        preferencesEditor.putString("list", jsonString);

        preferencesEditor.commit();
    }

    public static String[] getStringArray(ListAdapter adapter){
        String[] a = new String[adapter.getCount()];

        for(int i=0; i<a.length; i++)
            a[i] = adapter.getItem(i).toString();

        return a;
    }


}
