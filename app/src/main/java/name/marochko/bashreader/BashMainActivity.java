package name.marochko.bashreader;

/*
    Simple app to parse html.
*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BashMainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "marinfo";

    TextView textViewTemp;
    Button buttonLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bash_main);

        textViewTemp = (TextView)findViewById(R.id.textViewTemp);
        buttonLoad = (Button)findViewById(R.id.buttonLoad);
    }

    public void onButtonLoadClick(View v){

        textViewTemp.setText(BashParser.getPlainText());

    }
}
