package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.FavouritesRecipeListAdapter;
import com.example.cookbook.adapter.RecipesFromCategoryAdapter;
import com.example.cookbook.adapter.YourRecipesRecipeListAdapter;
import com.example.cookbook.model.Favourite;
import com.example.cookbook.model.RecipeDetail;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.model.RecipeTitle;
import com.example.cookbook.viewmodel.FavouritesViewModel;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment implements FavouritesRecipeListAdapter.OnListItemClickListener {

    private FavouritesViewModel favouritesViewModel;

    private RecyclerView recyclerViewFavourites;
    private FavouritesRecipeListAdapter recipeListAdapter;
    ArrayList<RecipeTitle> recipeLists = new ArrayList<>();

    private ArrayList<Favourite> myFavourites = new ArrayList<>();
    TextView noFavouritesText;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerViewFavourites = root.findViewById(R.id.favouritesRecyclerView);
        progressBar = root.findViewById(R.id.favouriteProgressBar);

        recyclerViewFavourites.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerViewFavourites.hasFixedSize();
        recipeListAdapter = new FavouritesRecipeListAdapter(recipeLists, this, this.getContext());
        recyclerViewFavourites.setAdapter(recipeListAdapter);

        noFavouritesText = root.findViewById(R.id.noFavourites);

        progressBar.setVisibility(View.VISIBLE);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        favouritesViewModel.getAllFavouriteIds().observe(getViewLifecycleOwner(), favourites -> {
            myFavourites.clear();

            if(favourites.isEmpty())
            {

            }
            else
            {
                for (Favourite f : favourites)
                {
                    myFavourites.add(f);
                }
            }

            favouritesViewModel.getMyRecipes(myFavourites);

            recipeListAdapter.notifyDataSetChanged();

        });

        favouritesViewModel.getMyRecipes().observe(getViewLifecycleOwner(), recipeTitles -> {
            progressBar.setVisibility(View.GONE);
            recipeLists.clear();

            recipeLists.addAll(recipeTitles);
            recipeListAdapter.notifyDataSetChanged();

            if(!recipeLists.isEmpty())
            {
                noFavouritesText.setVisibility(View.GONE);
            }
            else
                noFavouritesText.setVisibility(View.VISIBLE);

        });

    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("recipeId", recipeListAdapter.recipeLists.get(position).getId());

        Log.i("recipeID", bundle.getString("recipeId"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_favourites_to_navigation_recipe_detail, bundle);
    }
}