package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    TextView noFavouritesText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerViewFavourites = root.findViewById(R.id.favouritesRecyclerView);

        recyclerViewFavourites.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerViewFavourites.hasFixedSize();
        recipeListAdapter = new FavouritesRecipeListAdapter(recipeLists, this, this.getContext());
        recyclerViewFavourites.setAdapter(recipeListAdapter);

        noFavouritesText = root.findViewById(R.id.noFavourites);

/*

        recipeLists.add(new RecipeList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new RecipeList("Sushi", R.drawable.sushi));
        recipeLists.add(new RecipeList("Beef Wellington", R.drawable.wellington));
*/




/*
        favouritesViewModel.getRecipeTitles().observe(getViewLifecycleOwner(), recipeTitles -> {

            recipeLists.clear();
            favouritesViewModel.getAllFavouriteIds().observe(getViewLifecycleOwner(), favourites -> {
                for (RecipeTitle r : recipeTitles)
                {
                    for (Favourite f : favourites) {
                        if(r.getId().equals(f.getId()))
                        {
                            Log.i("fav1", f.getId());
                            Log.i("rec1", r.getId());
                            favouritesViewModel.searchForRecipeTitles(r.getTitle());
                            recipeLists.add(r);
                        }
                    }
                }
            });
            recipeListAdapter.notifyDataSetChanged();
        });
*/
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        favouritesViewModel.getAllFavouriteIds().observe(getViewLifecycleOwner(), favourites -> {
            recipeLists.clear();

            favouritesViewModel.getRecipeTitles().observe(getViewLifecycleOwner(), recipeTitles -> {
            for (Favourite f : favourites){
                for (RecipeTitle r:recipeTitles) {
                    if(r.getId().equals(f.getId()))
                    {
                        Log.i("favourite", f.getId());
                        Log.i("recipetitle", r.getId());
                        favouritesViewModel.searchForRecipeTitles(r.getTitle());
                        recipeLists.add(r);

                    }

                }
            }
                recipeListAdapter.notifyDataSetChanged();

            if(!recipeLists.isEmpty())
                noFavouritesText.setVisibility(View.GONE);
            });

        });
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("recipeId", recipeListAdapter.recipeLists.get(position).getId());

        Log.i("recipeID", bundle.getString("recipeId"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_favourites_to_navigation_recipe_detail, bundle);

        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}