package com.example.searchfunctionrra;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        // Initialize the ListView
        restaurantListView = findViewById(R.id.restaurantList);

        // Dummy data for restaurant types
        restaurantTypes = new ArrayList<>(Arrays.asList(
                "Abdul Restaurant",
                "Anjani Cuisine",
                "Oluwashola Foods",
                "Godwin Diner",
                "Joshua Cafeteria",
                "Frank Fast Food"
        ));

        // Create a custom adapter to use the custom layout for the list items
        adapter = new RestaurantAdapter(this, restaurantTypes);
        restaurantListView.setAdapter(adapter);

        // Handle item clicks with ripple effect
        restaurantListView.setOnItemClickListener((parent, view, position, id) -> {
            // Show the clicked restaurant name in a toast
            String selectedRestaurant = restaurantTypes.get(position);
            Toast.makeText(MainActivity.this, "Clicked: " + selectedRestaurant, Toast.LENGTH_SHORT).show();
        });

        // Set the app bar as the support action bar
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
                // Filter the restaurant list based on the search text
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
}
