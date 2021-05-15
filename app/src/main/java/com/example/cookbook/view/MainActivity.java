package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.cookbook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    EditText searchText;
    MenuItem searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //searchText = findViewById(R.id.searchText);
       // searchIcon = findViewById(R.id.searchIcon);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_categories, R.id.navigation_search, R.id.navigation_yourRecipes, R.id.navigation_favourites)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
/*
        searchIcon.setOnMenuItemClickListener( v -> {

            Bundle bundle = new Bundle();
            bundle.putString("title", searchText.getText().toString());

            Log.i("searched", bundle.getString("title"));
            navController.navigateUp();
            navController.navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);
            return true;
        });
        */

    }
    
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
       return super.onCreateOptionsMenu(menu);
        /*
        MenuItem menuItem = menu.findItem(R.id.searchIcon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Search recipes");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Bundle bundle = new Bundle();
                bundle.putString("title", searchView.getQuery().toString());

                Log.i("searched", bundle.getString("title"));

                Navigation.findNavController().navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

         */

    }
/*
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if(itemId == R.id.searchIcon)
        {
            Bundle bundle = new Bundle();
            bundle.putString("title", searchText.getText().toString());

            //Log.i("searched", bundle.getString(navController.getCurrentDestination().toString()));

            navController.navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);


            navController.navigateUp();
            navController.navigate(R.id.action_navigation_yourRecipes_to_navigation_recipes_from_category, bundle);
            navController.navigateUp();
            navController.navigate(R.id.action_navigation_favourites_to_navigation_recipes_from_category, bundle);
        }

        return super.onOptionsItemSelected(item);
    }

*/


}