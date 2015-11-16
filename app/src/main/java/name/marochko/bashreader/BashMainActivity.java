package name.marochko.bashreader;

/*
    Simple app to parse html.
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class BashMainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "marinfo";

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

        new BashParser(listViewQuotes, this).execute();

    }
}
