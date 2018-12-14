package bianquezi.com.br.cotamoeda.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import bianquezi.com.br.cotamoeda.R;
import bianquezi.com.br.cotamoeda.constant.CoinConstants;
import bianquezi.com.br.cotamoeda.entity.Coin;
import bianquezi.com.br.cotamoeda.infrastructure.InternetNotAvailableException;
import bianquezi.com.br.cotamoeda.repository.CoinRepository;
import bianquezi.com.br.cotamoeda.utils.JsonToCoinConverter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MAIN_ACTIVITY";
    private ViewHolder mViewHolder;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewHolder = new ViewHolder();
        mViewHolder.bitcoin = findViewById(R.id.bitcoin);
        mViewHolder.dollar = findViewById(R.id.dollar);
        mViewHolder.euro = findViewById(R.id.euro);
        mViewHolder.peso = findViewById(R.id.peso_argentino);
        mViewHolder.libra = findViewById(R.id.libra);

        load = ProgressDialog.show(this, "Por favor aguarde", "Baixando dados...");

        CoinRepository coinRepository = new CoinRepository(MainActivity.this);
        coinRepository.execute();

        try {

            String typeCoin = coinRepository.get();
            Log.i(TAG, typeCoin);

            JsonToCoinConverter jsonToCoinConverterBitcoin = new JsonToCoinConverter();
            jsonToCoinConverterBitcoin.execute(typeCoin, CoinConstants.NAME.BITCOIN);
            Coin bitcoin = jsonToCoinConverterBitcoin.get();

            JsonToCoinConverter jsonToCoinConverterDolar = new JsonToCoinConverter();
            jsonToCoinConverterDolar.execute(typeCoin, CoinConstants.NAME.DOLAR);
            Coin dolar = jsonToCoinConverterDolar.get();

            JsonToCoinConverter jsonToCoinConverterEuro = new JsonToCoinConverter();
            jsonToCoinConverterEuro.execute(typeCoin, CoinConstants.NAME.EURO);
            Coin euro = jsonToCoinConverterEuro.get();

            JsonToCoinConverter jsonToCoinConverterLibra = new JsonToCoinConverter();
            jsonToCoinConverterLibra.execute(typeCoin, CoinConstants.NAME.LIBRA);
            Coin libra = jsonToCoinConverterLibra.get();

            JsonToCoinConverter jsonToCoinConverterPeso = new JsonToCoinConverter();
            jsonToCoinConverterPeso.execute(typeCoin, CoinConstants.NAME.PESO_ARGENTINO);
            Coin peso = jsonToCoinConverterPeso.get();

            mViewHolder.bitcoin.setText(CoinConstants.SIGN.REAL_SIGN + String.valueOf(bitcoin.getBuy()));
            mViewHolder.dollar.setText(CoinConstants.SIGN.REAL_SIGN + String.valueOf(dolar.getBuy()));
            mViewHolder.euro.setText(CoinConstants.SIGN.REAL_SIGN + String.valueOf(euro.getBuy()));
            mViewHolder.libra.setText(CoinConstants.SIGN.REAL_SIGN + String.valueOf(libra.getBuy()));
            mViewHolder.peso.setText(CoinConstants.SIGN.REAL_SIGN + String.valueOf(peso.getBuy()));

        } catch (InterruptedException e) {
            Log.i(TAG, "Erro baixar Json da API: ****** " + e + " ******");
        } catch (ExecutionException e) {
            Log.i(TAG, "Erro ao converter json em Coin: ****** " + e + " ******");
        }finally {
            load.dismiss();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_help) {
            // Handle the camera action
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static class ViewHolder{
        private TextView dollar;
        private TextView euro;
        private TextView libra;
        private TextView peso;
        private TextView bitcoin;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        load.dismiss();
    }
}
