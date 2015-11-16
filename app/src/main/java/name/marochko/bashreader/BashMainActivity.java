package name.marochko.bashreader;

/**
 * Simple app to parse html from bash.im site
 * (it contains funny quotes at russian language).
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;



public class BashMainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "marinfo";

//    Simplest interface: list and button to (re)load quotes from site.
    ListView listViewQuotes;
    Button buttonLoad;

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
        SavePreferences.restoreData(listViewQuotes, this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SavePreferences.saveData(listViewQuotes);
    }

}
