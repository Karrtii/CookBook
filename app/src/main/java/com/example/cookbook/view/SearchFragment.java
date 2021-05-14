package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.RecipesFromCategoryAdapter;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.viewmodel.RecipesFromCategoryViewModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements RecipesFromCategoryAdapter.OnListItemClickListener{

    private RecipesFromCategoryViewModel recipesFromCategoryViewModel;
    private RecyclerView recyclerView;
    private RecipesFromCategoryAdapter recipeListAdapter;

    EditText searchText;
    Button searchButton;

    ArrayList<RecipeTitle> recipeLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recipesFromCategoryViewModel =
                new ViewModelProvider(this).get(RecipesFromCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        //String title = getArguments().getString("title");

        recyclerView = root.findViewById(R.id.recipesFromCategoriesRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.hasFixedSize();

        recipeListAdapter = new RecipesFromCategoryAdapter(recipeLists, this, this.getContext());
        recyclerView.setAdapter(recipeListAdapter);

        searchText = root.findViewById(R.id.searchText);
        searchButton = root.findViewById(R.id.searchIcon);

        searchButton.setOnClickListener(v -> {
            String title = searchText.getText().toString();
            if(!title.isEmpty()) {
                recipesFromCategoryViewModel.searchForRecipeTitles(title);
            }

            recipesFromCategoryViewModel.getRecipeTitles().observe(getViewLifecycleOwner(), recipeTitles -> {
                recipeLists.clear();
                recipeLists.addAll(recipeTitles);
                recipeListAdapter.notifyDataSetChanged();
        });
        });

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("recipeId", recipeListAdapter.recipeLists.get(position).getId());

        Log.i("recipeID", bundle.getString("recipeId"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_search_to_navigation_recipe_detail, bundle);

        Log.i("TAG", "in recipes from category ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}
