package name.marochko.bashreader;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Just to save and restore list data.
 */

public class SavePreferences {

    private static SharedPreferences.Editor preferencesEditor;
    private static SharedPreferences preferences;

    public static void saveData(ListView listViewQuotes){

//      converting list data to json and saving
        preferencesEditor = preferences.edit();
        Gson gson = new Gson();
        String[] array = getStringArray(listViewQuotes.getAdapter());
        String jsonString = gson.toJson(array);
        preferencesEditor.putString("list", jsonString);
        preferencesEditor.apply();


    }

    public static void restoreData(ListView listViewQuotes, Activity activity){


//      from Preferences to List<String>
        preferences = activity.getPreferences(activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString;
        jsonString = preferences.getString("list", "");
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> stringList = gson.fromJson(jsonString, type);


//      and now from List<String> to String[], then setting up the adapter
        if(stringList != null){
            String[] array = stringList.toArray(new String[1]);
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<>(activity, R.layout.quotes_list_item, array);
            listViewQuotes.setAdapter(adapter);
        }
    }


    public static String[] getStringArray(ListAdapter adapter){
        String[] a = new String[adapter.getCount()];

        for(int i=0; i<a.length; i++)
            a[i] = adapter.getItem(i).toString();

        return a;
    }

}
