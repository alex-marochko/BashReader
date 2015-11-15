package name.marochko.bashreader;

/**
 * Created by SDN on 16.11.2015.
 */

import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class BashParser {

    private final static String LOG_TAG = "marinfo";
    private final static String URL = "http://bash.im/random";
    private final static int QUOTES_PER_PAGE = 50;
    private final static String TAG_TO_SELECT = "div.text";

    public static String getPlainText() {

        Document doc;
        String plainText = null;

        try {
            doc = Jsoup.connect(URL).get();
            plainText = doc.text();
        } catch (IOException ioe) {

            Log.e(LOG_TAG, "IO Exception: ", ioe);
        }

        return plainText;
    }
}


