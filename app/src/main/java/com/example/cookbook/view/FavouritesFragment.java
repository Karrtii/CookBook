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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.FavouritesRecipeListAdapter;
import com.example.cookbook.adapter.YourRecipesRecipeListAdapter;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.viewmodel.FavouritesViewModel;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment implements FavouritesRecipeListAdapter.OnListItemClickListenerFavourites {

    private FavouritesViewModel favouritesViewModel;

    private RecyclerView recyclerViewFavourites;
    private FavouritesRecipeListAdapter recipeListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerViewFavourites = root.findViewById(R.id.favouritesRecyclerView);

        recyclerViewFavourites.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewFavourites.hasFixedSize();

        ArrayList<RecipeList> recipeLists = new ArrayList<>();
        recipeLists.add(new RecipeList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new RecipeList("Sushi", R.drawable.sushi));
        recipeLists.add(new RecipeList("Beef Wellington", R.drawable.wellington));

        recipeListAdapter = new FavouritesRecipeListAdapter(recipeLists, this);
        recyclerViewFavourites.setAdapter(recipeListAdapter);

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;
        Log.i("TAGYR", "favourites: ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}