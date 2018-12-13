package bianquezi.com.br.cotamoeda.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 *    author: Paulo Sérgio do Nascimento
 *    date: 13/12/2018
 *
 *    Classe responsável por verificar se existe conexão com a internet
 *
 * */

public class NetWorkUtils {

    public static Boolean isConnectionAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }
}
