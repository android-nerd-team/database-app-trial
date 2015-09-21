package com.example.recepinanc.simpledatabaseapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button mButton;
    private ListView customListView;
    private MyListAdapter myListAdapter;
    private List<Country> countries;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),addCountryActivity.class);
                startActivity(a);
            }
        });
        customListView = (ListView) findViewById(R.id.listView);

        SharedPreferences settings = getSharedPreferences("SQL", 0);
        boolean firstTime = settings.getBoolean("firstTime", true);

        if (firstTime) {
            dbHelper.insertCountry(new Country("Turkiye", "90"));
            dbHelper.insertCountry(new Country("Amerika", "1"));
            dbHelper.insertCountry(new Country("Ingiltere", "44"));
            dbHelper.insertCountry(new Country("Almanya", "49"));

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
        }

        countries = dbHelper.getAllCountries();
        myListAdapter = new MyListAdapter(MainActivity.this, countries);
        customListView.setAdapter(myListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

//        myListAdapter = new MyListAdapter(this,countries);
//        customListView.setAdapter(myListAdapter);
//        myListAdapter.notifyDataSetChanged();



        myListAdapter = new MyListAdapter(MainActivity.this, dbHelper.getAllCountries());

        customListView.setAdapter(myListAdapter);

        customListView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
