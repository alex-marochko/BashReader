package name.marochko.bashreader;

/**
 * Created by SDN on 16.11.2015.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BashParser extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = "marinfo";
    private final String URL = "http://bash.im/random";
    private final int QUOTES_PER_PAGE = 50;
    private final String TAG_TO_SELECT = "div.text";
    private ListView listView;
    private String plainText = null;
    private String[] strArray = new String[QUOTES_PER_PAGE];
    private Context context;
    ArrayAdapter<String> adapter;

    BashParser(ListView listView, Context context){
        this.listView = listView;
        this.context = context;

        Log.i(LOG_TAG, "BashParser created");
    }

    public void getList() {
        Document doc;
        Elements els;
        try {
            doc = Jsoup.connect(URL).get();

            els = doc.select(TAG_TO_SELECT);

            int i = 0;

            for(Element el:els ){
                el = el.html(el.html().replace("<br>", "zzzxxxccc"));
                strArray[i++]=el.text().replace("zzzxxxccc", "\n");
            }

            adapter = new ArrayAdapter<>(context, R.layout.quotes_list_item, strArray);


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "IO Exception: ", ioe);
        }

        Log.i(LOG_TAG, "getList() end");
    }

    @Override
    protected Void doInBackground(Void... params) {
        getList();
        Log.i(LOG_TAG, "doInBackground(Void... params) end");
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        listView.setAdapter(adapter);
        Log.i(LOG_TAG, "onPostExecute(Void result) end");
    }
}


