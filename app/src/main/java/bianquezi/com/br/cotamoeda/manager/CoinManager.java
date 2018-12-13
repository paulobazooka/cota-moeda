package bianquezi.com.br.cotamoeda.manager;

import android.content.Context;
import android.os.AsyncTask;

public class CoinManager {

    private Context context;

    public CoinManager(Context context) {
        this.context = context;
    }


    public void create(){
        AsyncTask<Void, Void, Boolean> task = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Boolean result){

            }


        };
    }
}
