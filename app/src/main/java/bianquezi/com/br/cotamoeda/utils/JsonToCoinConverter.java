package bianquezi.com.br.cotamoeda.utils;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import bianquezi.com.br.cotamoeda.constant.CoinConstants;
import bianquezi.com.br.cotamoeda.entity.Coin;

public class JsonToCoinConverter extends AsyncTask<String, Integer, Coin> {


    @Override
    protected Coin doInBackground(String... strings) {

        try {
            JSONObject response = new JSONObject(strings[0]);
            JSONObject results = response.getJSONObject("results");

            JSONObject currencies = results.getJSONObject("currencies");
            //  Log.i(TAG, "CURRENCIES: " + currencies.toString());

            Gson gson = new Gson();

            Coin coin = gson.fromJson(currencies.getString(strings[1]), Coin.class);

            return coin;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Coin coin) {
        super.onPostExecute(coin);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
