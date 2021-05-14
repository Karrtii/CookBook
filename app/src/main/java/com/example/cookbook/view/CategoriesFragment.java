package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.CategoriesListAdapter;
import com.example.cookbook.model.CategoryList;
import com.example.cookbook.viewmodel.CategoriesViewModel;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment implements CategoriesListAdapter.OnListItemClickListener{

    private CategoriesViewModel categoriesViewModel;

    private RecyclerView recyclerViewCategories;
    private CategoriesListAdapter recipeListAdapter;
    private TextView nocategoriesText;

    ArrayList<CategoryList> recipeLists = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        nocategoriesText = root.findViewById(R.id.noCategoriesText);
        recyclerViewCategories = root.findViewById(R.id.categoriesRecyclerView);

        recyclerViewCategories.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewCategories.hasFixedSize();

        recipeListAdapter = new CategoriesListAdapter(recipeLists, this, this.getContext());
        recyclerViewCategories.setAdapter(recipeListAdapter);


        categoriesViewModel.getCategories().observe(getViewLifecycleOwner(), categoryLists -> {
            recipeLists.clear();


            recipeLists.addAll(categoryLists);
            recipeListAdapter.notifyDataSetChanged();

            if(!recipeLists.isEmpty())
                nocategoriesText.setVisibility(View.GONE);
        });

        nocategoriesText.setVisibility(View.VISIBLE);
/*

        recipeLists.add(new CategoryList("Fried Chicken", R.drawable.friedchicken));
        recipeLists.add(new CategoryList("French Fries", R.drawable.fries));
        recipeLists.add(new CategoryList("Butter Chicken", R.drawable.butterchicken));
        recipeLists.add(new CategoryList("Ice Cream", R.drawable.icecream));
        recipeLists.add(new CategoryList("Nasi Lemak", R.drawable.nasilemak));
        recipeLists.add(new CategoryList("Pasta", R.drawable.pasta));
        recipeLists.add(new CategoryList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new CategoryList("Pizza", R.drawable.pizza));
        recipeLists.add(new CategoryList("Spaghetti", R.drawable.spaghetti));
        recipeLists.add(new CategoryList("Sushi", R.drawable.sushi));
        recipeLists.add(new CategoryList("Beef Wellington", R.drawable.wellington));
*/

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("title", recipeListAdapter.recipeLists.get(position).getTitle());

        Log.i("gat", bundle.getString("title"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);

        Log.i("TAG", "categories: ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();

    }
/*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.searchIcon);
        MenuItem sendItem = menu.findItem(R.id.sendIcon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search recipes");

        sendItem.setOnMenuItemClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", searchView.getQuery().toString());

            Log.i("searched", bundle.getString("title"));

            Navigation.findNavController(getView()).navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);

            return true;
        });
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item, @NonNull MenuInflater inflater) {
        int itemId = item.getItemId();

        inflater.inflate(R.menu.main_menu, item);
        MenuItem searchItem = item.findItem(R.id.searchIcon);
        MenuItem sendItem = item.findItem(R.id.sendIcon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if(itemId == R.id.sendIcon)
        {


            Bundle bundle = new Bundle();
            bundle.putString("title", searchText.getText().toString());

            Log.i("searched", bundle.getString("title"));

            Navigation.findNavController(getView()).navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);
        }

        return super.onOptionsItemSelected(item);
    }

 */

}