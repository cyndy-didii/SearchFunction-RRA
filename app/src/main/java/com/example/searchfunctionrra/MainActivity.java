package com.example.searchfunctionrra;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView restaurantListView;
    private RestaurantAdapter adapter;
    private ArrayList<String> restaurantTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantListView = findViewById(R.id.restaurantList);

        restaurantTypes = new ArrayList<>(Arrays.asList(
                "Abdul Restaurant",
                "Anjani Cuisine",
                "Oluwashola Foods",
                "Godwin Diner",
                "Joshua Cafeteria",
                "Frank Fast Food"
        ));

        adapter = new RestaurantAdapter(this, restaurantTypes);
        restaurantListView.setAdapter(adapter);

        restaurantListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRestaurant = restaurantTypes.get(position);
            Toast.makeText(MainActivity.this, "Clicked: " + selectedRestaurant, Toast.LENGTH_SHORT).show();
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
}
