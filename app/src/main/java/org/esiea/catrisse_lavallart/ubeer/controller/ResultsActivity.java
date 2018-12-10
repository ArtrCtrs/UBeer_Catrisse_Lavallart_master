package org.esiea.catrisse_lavallart.ubeer.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.esiea.catrisse_lavallart.ubeer.view.BarsAdapter;
import org.esiea.catrisse_lavallart.ubeer.R;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ResultsActivity extends AppCompatActivity {
    public static final String COORDINATES_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";

    private static final String PLACES_API_KEY ="AIzaSyDh4ghFcDx-C5i9u4xFosBV47D0x_7DcZE";
    private BarsAdapter bAdapter;
    protected Context context;
    private Bar[] BarArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("TEST","CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        context = getApplicationContext();

        final RecyclerView barsView = (RecyclerView) findViewById(R.id.rv_bars);
        final Context context = getApplicationContext();

        barsView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fillBars();
        bAdapter = new BarsAdapter(BarArray);
        barsView.setAdapter(bAdapter);

    }

    public void fillBars() {
        try {
            JSONArray JSONBarArray = getCoordinatesFromFile().getJSONArray("results");
            int JSONBarArrayLength = JSONBarArray.length();

            BarArray = new Bar[JSONBarArrayLength];

            for (int i = 0; i < JSONBarArrayLength; i++) {
                JSONObject tmpJSONBar = JSONBarArray.getJSONObject(i);


                String name;
                String address;
                String isOpen;
                String rank;
                String imageUrl="";

                if (tmpJSONBar.has("opening_hours")) {
                    if (tmpJSONBar.getJSONObject("opening_hours").has("open_now")) {
                        isOpen = tmpJSONBar.getJSONObject("opening_hours").getString("open_now");
                    } else {
                        isOpen = "?";
                    }
                } else {
                    isOpen = "?";
                }
                if (tmpJSONBar.has("rating")) {
                    rank = tmpJSONBar.getString("rating");
                } else {
                    rank = "?";
                }
                if (tmpJSONBar.has("name")) {
                    name = tmpJSONBar.getString("name");
                } else {
                    name = "?";
                }
                if (tmpJSONBar.has("vicinity")) {
                    address = tmpJSONBar.getString("vicinity");
                } else {
                    address = "?";
                }
                if (tmpJSONBar.has("photos")) {
                    Log.w("TEST","photos");
                    if (tmpJSONBar.getJSONArray("photos").getJSONObject(0).has("photo_reference")) {
                        Log.w("TEST","secondifphoto");
                        imageUrl="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
                        imageUrl += tmpJSONBar.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                        imageUrl+="&sensor=false&key="+PLACES_API_KEY;
                        //imageUrl="https://lh3.googleusercontent.com/p/AF1QipPjjEozAmJxLdmd0BA1RO_nf5GazdlkkwXWtSmq=s1600-w400";
                    }
                }else {
                        imageUrl = "https://cooking.minguet.fr/imgs/placeholder.png";

                }

                BarArray[i] = new Bar(name, address, isOpen, rank, imageUrl);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.New:
                Intent j = new Intent(context, SetupActivity.class);
                j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(j);
                break;
        }
        return true;
    }


    public JSONObject getCoordinatesFromFile() {  //JsonArray
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bars.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONObject(new String(buffer, "UTF-8")); // construction du tableau
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }


}
