package org.esiea.catrisse_lavallart.ubeer.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.esiea.catrisse_lavallart.ubeer.R;
import org.esiea.catrisse_lavallart.ubeer.data.Utils;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;


public class BarDetailActivity extends AppCompatActivity {
    Bar selectedBar;
    TextView title;
    TextView address;
    TextView isOpen;
    TextView rank;
    Context context;
    ImageView imageView;
    Button addFavoriteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);

        context = getApplicationContext();

        title=(TextView)findViewById(R.id.title);
        address=(TextView)findViewById(R.id.address);
        isOpen=(TextView)findViewById(R.id.isOpen);
        rank=(TextView)findViewById(R.id.rank);
        imageView=(ImageView) findViewById(R.id.imageView);

        addFavoriteButton = (Button)findViewById(R.id.addFavorite);

        selectedBar = (Bar)getIntent().getSerializableExtra("selectedBar");

        title.setText(selectedBar.getName());
        address.setText(selectedBar.getAddress());
        rank.setText(selectedBar.getRank());

        String openString;
        if(selectedBar.getIsOpen().equals("true")){
            openString=getString(R.string.open);
        }else if(selectedBar.getIsOpen().equals("false")){
            openString=getString(R.string.closed);
        }else{
            openString="?";
        }
        isOpen.setText(openString);
        Picasso.with(context)
                .load(selectedBar.getUrl())
                .resize(400,400)
                .into(imageView);
        //imageView.setBackground(Utils.LoadImageFromWebOperations(selectedBar.getUrl()));

        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAddFavorite();
            }
        });
    }

    public void clickAddFavorite(){
        Log.d("TEST","clickaddfav");

        //add to bdd :
        //
        //selectedBar.getName()
        //selectedBar.getAddress()
        //selectedBar.getRank()
        //selectedBar.getIsOpen()
        //selectedBar.getUrl()
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
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
}
