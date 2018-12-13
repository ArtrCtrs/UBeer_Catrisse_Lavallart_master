package org.esiea.catrisse_lavallart.ubeer.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.esiea.catrisse_lavallart.ubeer.data.BarBDD;
import org.esiea.catrisse_lavallart.ubeer.view.BarsAdapter;
import org.esiea.catrisse_lavallart.ubeer.R;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;
import org.esiea.catrisse_lavallart.ubeer.view.BarsFavAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class FavoritesActivity extends AppCompatActivity {

    private static final String PLACES_API_KEY ="AIzaSyDh4ghFcDx-C5i9u4xFosBV47D0x_7DcZE";

    private BarsFavAdapter bAdapter;
    protected Context context;
    private Bar[] barArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        final RecyclerView barsView = (RecyclerView) findViewById(R.id.rv_bars_favs);
        barsView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        context = getApplicationContext();

        getBarsFromDatabase();

        bAdapter = new BarsFavAdapter(barArray);
        barsView.setAdapter(bAdapter);
        Log.d("BDD","endOnCerate");


    }

    public void getBarsFromDatabase(){
        BarBDD bd = new BarBDD(this);
        bd.open();
        barArray= bd.getBars();
        for(Bar b: barArray){
            Log.d("BDD",b.getName());
        }

        bd.close();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Fav:
                Intent i = new Intent(context, FavoritesActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.New:
                Intent j = new Intent(context, SetupActivity.class);
                //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(j);
                break;
        }
        return true;
    }



}
