package com.example.cookbook.view;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.RecipesFromCategoryAdapter;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.viewmodel.RecipesFromCategoryViewModel;

import java.util.ArrayList;

public class RecipesFromCategoryFragment extends Fragment implements RecipesFromCategoryAdapter.OnListItemClickListener{

    private RecipesFromCategoryViewModel recipesFromCategoryViewModel;
    private RecyclerView recyclerView;
    private RecipesFromCategoryAdapter recipeListAdapter;

    ArrayList<RecipeTitle> recipeLists = new ArrayList<>();

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        recipesFromCategoryViewModel =
                new ViewModelProvider(this).get(RecipesFromCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipes_from_category, container, false);

        recyclerView = root.findViewById(R.id.recipesFromCategoriesRecyclerView);
        progressBar = root.findViewById(R.id.recipesFromCategoriesProgressBar);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.hasFixedSize();

        recipeListAdapter = new RecipesFromCategoryAdapter(recipeLists, this, this.getContext());
        recyclerView.setAdapter(recipeListAdapter);

        String title = getArguments().getString("title");

        recipesFromCategoryViewModel.searchForRecipeTitles(title);

        recipesFromCategoryViewModel.getRecipeTitles().observe(getViewLifecycleOwner(), recipeTitles -> {
            progressBar.setVisibility(View.GONE);
            recipeLists.clear();
            recipeLists.addAll(recipeTitles);
            recipeListAdapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("recipeId", recipeListAdapter.recipeLists.get(position).getId());

        Log.i("recipeID", bundle.getString("recipeId"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_recipes_from_category_to_navigation_recipe_detail, bundle);

        Log.i("TAG", "in recipes from category ");
    }
}
