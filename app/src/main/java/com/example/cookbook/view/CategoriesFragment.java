package com.example.cookbook.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.RecipeListAdapter;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.viewmodel.CategoriesViewModel;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel homeViewModel;

    private RecyclerView recyclerViewCategories;
    private RecipeListAdapter recipeListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerViewCategories = root.findViewById(R.id.categoriesRecyclerView);

        recyclerViewCategories.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewCategories.hasFixedSize();
/*
        ArrayList<RecipeList> recipeLists = new ArrayList<>();
        recipeLists.add(new RecipeList("Fried Chicken", R.drawable.friedchicken));
        recipeLists.add(new RecipeList("French Fries", R.drawable.fries));
        recipeLists.add(new RecipeList("Butter Chicken", R.drawable.butterchicken));
        recipeLists.add(new RecipeList("Ice Cream", R.drawable.icecream));
        recipeLists.add(new RecipeList("Nasi Lemak", R.drawable.nasilemak));
        recipeLists.add(new RecipeList("Pasta", R.drawable.pasta));
        recipeLists.add(new RecipeList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new RecipeList("Pizza", R.drawable.pizza));
        recipeLists.add(new RecipeList("Spaghetti", R.drawable.spaghetti));
        recipeLists.add(new RecipeList("Sushi", R.drawable.sushi));
        recipeLists.add(new RecipeList("Beef Wellington", R.drawable.wellington));


        recipeListAdapter = new RecipeListAdapter(recipeLists, this.getContext());
        recyclerViewCategories.setAdapter(recipeListAdapter);
*/
        return root;
    }

    @Override
    public void onResume() {
        ArrayList<RecipeList> recipeLists = new ArrayList<>();
        recipeLists.add(new RecipeList("Fried Chicken", R.drawable.friedchicken));
        recipeLists.add(new RecipeList("French Fries", R.drawable.fries));
        recipeLists.add(new RecipeList("Butter Chicken", R.drawable.butterchicken));
        recipeLists.add(new RecipeList("Ice Cream", R.drawable.icecream));
        recipeLists.add(new RecipeList("Nasi Lemak", R.drawable.nasilemak));
        recipeLists.add(new RecipeList("Pasta", R.drawable.pasta));
        recipeLists.add(new RecipeList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new RecipeList("Pizza", R.drawable.pizza));
        recipeLists.add(new RecipeList("Spaghetti", R.drawable.spaghetti));
        recipeLists.add(new RecipeList("Sushi", R.drawable.sushi));
        recipeLists.add(new RecipeList("Beef Wellington", R.drawable.wellington));


        recipeListAdapter = new RecipeListAdapter(recipeLists, this.getContext());
        recyclerViewCategories.setAdapter(recipeListAdapter);
        super.onResume();
    }
}