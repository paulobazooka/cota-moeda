package bianquezi.com.br.cotamoeda.repository;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import bianquezi.com.br.cotamoeda.constant.CoinConstants;
import bianquezi.com.br.cotamoeda.entity.Coin;
import bianquezi.com.br.cotamoeda.infrastructure.InternetNotAvailableException;
import bianquezi.com.br.cotamoeda.utils.NetWorkUtils;

public class CoinRepository extends AsyncTask<Void, Integer, String>{

    private Context mContext;


    public CoinRepository(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            return this.getCoins();
        } catch (InternetNotAvailableException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    private String getCoins() throws InternetNotAvailableException {

        if(!NetWorkUtils.isConnectionAvailable(mContext)){
            throw new InternetNotAvailableException();
        }

        InputStream inputStream;
        HttpURLConnection connection;
        URL url;
        String response;

        try {
            url = new URL(CoinConstants.URL.GET_URL + CoinConstants.KEY.KEY);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(150000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "Application/x-www-form-urlencoded");
            connection.setRequestProperty("chaset","utf-8");
            connection.setUseCaches(false);

            connection.connect();

            if(connection.getResponseCode() == CoinConstants.STATUS_CODE.SUCCESS){
                inputStream = connection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }else{
                inputStream = connection.getErrorStream();
                response = getStringFromInputStream(inputStream);
            }

            inputStream.close();
            connection.disconnect();

            return response;
        }catch (Exception e){
            return "";
        }
    }

    private String getStringFromInputStream(InputStream inputStream) {
        if(inputStream == null){
            return "";
        }else{
            BufferedReader bufferedReader;
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try{

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);                }

                return stringBuilder.toString();

            }catch (Exception e){
                return "";
            }
        }
    }

}
