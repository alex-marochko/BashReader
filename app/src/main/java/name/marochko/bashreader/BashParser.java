package name.marochko.bashreader;

/**
 * Created by SDN on 16.11.2015.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BashParser extends AsyncTask<Void, Void, Void> {

    private final static String LOG_TAG = "marinfo";
    private final static String URL = "http://bash.im/random";
    private final static int QUOTES_PER_PAGE = 50;
    private final static String TAG_TO_SELECT = "div.text";
    TextView textView;
    private static String plainText = null;

    BashParser(TextView textView){
        this.textView = textView;
        Log.i(LOG_TAG, "BashParser created");
    }

    public void getPlainText() {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
            plainText = doc.text();
        } catch (IOException ioe) {
            Log.e(LOG_TAG, "IO Exception: ", ioe);
        }

        Log.i(LOG_TAG, "getPlainText() end");
    }

    @Override
    protected Void doInBackground(Void... params) {
        getPlainText();
        Log.i(LOG_TAG, "doInBackground(Void... params) end");
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        textView.setText(plainText);
        Log.i(LOG_TAG, "onPostExecute(Void result) end");
    }
}


