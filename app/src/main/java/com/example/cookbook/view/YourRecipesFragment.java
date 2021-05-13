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
import com.example.cookbook.adapter.YourRecipesRecipeListAdapter;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.viewmodel.YourRecipesViewModel;

import java.util.ArrayList;

public class YourRecipesFragment extends Fragment implements YourRecipesRecipeListAdapter.OnListItemClickListenerYourRecipes {

    private YourRecipesViewModel yourRecipesViewModel;

    private RecyclerView recyclerViewYourRecipes;
    private YourRecipesRecipeListAdapter recipeListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourRecipesViewModel =
                new ViewModelProvider(this).get(YourRecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_your_recipes, container, false);

        recyclerViewYourRecipes = root.findViewById(R.id.yourRecipesRecyclerView);

        recyclerViewYourRecipes.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewYourRecipes.hasFixedSize();

        ArrayList<RecipeList> recipeLists = new ArrayList<>();
        recipeLists.add(new RecipeList("Pasta", R.drawable.pasta));
        recipeLists.add(new RecipeList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new RecipeList("Pizza", R.drawable.pizza));
        recipeLists.add(new RecipeList("Spaghetti", R.drawable.spaghetti));
        recipeLists.add(new RecipeList("Sushi", R.drawable.sushi));
        recipeLists.add(new RecipeList("Beef Wellington", R.drawable.wellington));

        recipeListAdapter = new YourRecipesRecipeListAdapter(recipeLists, this);
        recyclerViewYourRecipes.setAdapter(recipeListAdapter);

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;
        Log.i("TAGYR", "your recipes: ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}