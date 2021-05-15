package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.YourRecipesRecipeListAdapter;
import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.viewmodel.YourRecipesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class YourRecipesFragment extends Fragment implements YourRecipesRecipeListAdapter.OnListItemClickListenerYourRecipes {

    private YourRecipesViewModel yourRecipesViewModel;

    private RecyclerView recyclerViewYourRecipes;
    private YourRecipesRecipeListAdapter recipeListAdapter;

    ArrayList<AddRecipe> recipeLists = new ArrayList<>();

    FloatingActionButton floatingActionButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourRecipesViewModel =
                new ViewModelProvider(this).get(YourRecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_your_recipes, container, false);

        recyclerViewYourRecipes = root.findViewById(R.id.yourRecipesRecyclerView);

        recyclerViewYourRecipes.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerViewYourRecipes.hasFixedSize();

        recipeListAdapter = new YourRecipesRecipeListAdapter(recipeLists, this, this.getContext());
        recyclerViewYourRecipes.setAdapter(recipeListAdapter);

        floatingActionButton = root.findViewById(R.id.floating);



        floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_navigation_yourRecipes_to_navigation_add_recipe);
        });

        yourRecipesViewModel.getAllRecipes().observe(getViewLifecycleOwner(), addRecipes -> {
            recipeLists.clear();
            recipeLists.addAll(addRecipes);
            recipeListAdapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putInt("id", recipeListAdapter.recipeLists.get(position).getId());

        Log.i("recipeID", String.valueOf(bundle.getInt("id")));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_yourRecipes_to_navigation_view_added_recipe, bundle);

        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}