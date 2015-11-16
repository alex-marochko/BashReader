package name.marochko.bashreader;

/**
 * AsyncTask-based parsing class.
 * The point is to get all loading and parsing work done here.
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

//    constants
    private final String LOG_TAG = "marinfo";
    private final String URL = "http://bash.im/random";
    private final int QUOTES_PER_PAGE = 50;
    private final String TAG_TO_SELECT = "div.text";

//    other declarations
    private ListView listView;
    private String[] strArray = new String[QUOTES_PER_PAGE];
    private Context context;
    ArrayAdapter<String> adapter;

//    constructor
    BashParser(ListView listView, Context context){
        this.listView = listView;
        this.context = context;
        Log.i(LOG_TAG, "BashParser created");
    }


//    main loading and parsing work is in this method
    public void getList() {
        Document doc;
        Elements els;
        try {
//            loading html from site into Jsoup object
            doc = Jsoup.connect(URL).get();
//            selecting tags with quotes only
            els = doc.select(TAG_TO_SELECT);

            int i = 0;
//            for every Element with quote
            for(Element el:els ){
//                Jsoup is deleting <br> tags when converts html to text, so
//                we are replacing <br>'s with our 'mark string'
                el = el.html(el.html().replace("<br>", "zzzxxxccc"));
//                and then replacing 'mark' with java's 'new line' symbol, writing result to array
                strArray[i++]=el.text().replace("zzzxxxccc", "\n");
            }
//            initializing list adapter with array
            adapter = new ArrayAdapter<>(context, R.layout.quotes_list_item, strArray);

        } catch (IOException ioe) {
            Log.e(LOG_TAG, "IO Exception: ", ioe);
        }

        Log.i(LOG_TAG, "getList() end");
    }

//    method that will start on .execute() at background thread
    @Override
    protected Void doInBackground(Void... params) {
        getList();
        Log.i(LOG_TAG, "doInBackground(Void... params) end");
        return null;
    }


//    this is sending the result of background work into UI thread
    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        listView.setAdapter(adapter);
        Log.i(LOG_TAG, "onPostExecute(Void result) end");
    }
}


