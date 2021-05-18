package com.example.cookbook.view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookbook.R;
import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.viewmodel.AddRecipeViewModel;
import com.example.cookbook.viewmodel.ViewAddedRecipeViewModel;

import java.util.Random;

public class ViewAddedRecipeFragment extends Fragment {

    private ViewAddedRecipeViewModel viewAddedRecipeViewModel;

    TextView title, ingredients, steps;
    ImageView image;
    Button deleteButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewAddedRecipeViewModel  =
                new ViewModelProvider(this).get(ViewAddedRecipeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_added_recipes, container, false);


        title = root.findViewById(R.id.viewAddedRecipeTitle);
        ingredients = root.findViewById(R.id.viewAddedIngredients);
        steps = root.findViewById(R.id.viewAddedSteps);
        image = root.findViewById(R.id.viewAddedImage);
        deleteButton = root.findViewById(R.id.deleteRecipeButton);

        //Setting the picture to a random food pictures included in @drawable since I cannot save and get the image from gallery in SQLite
        final TypedArray imgs = getResources().obtainTypedArray(R.array.randomFoodImages);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);
        image.setImageResource(resID);

        int id = getArguments().getInt("id");

        Log.i("idint", String.valueOf(id));

        viewAddedRecipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), recipes -> {
            for (AddRecipe r : recipes) {
                if(r.getId() == id)
                {
                    title.setText(r.getTitle());
                    ingredients.setText(r.getIngredients());
                    steps.setText(r.getSteps());

                    deleteButton.setOnClickListener(v -> {
                        viewAddedRecipeViewModel.delete(r);
                        Navigation.findNavController(getView()).navigate(R.id.action_navigation_view_added_recipe_to_navigation_yourRecipes);

                    });
                }
            }
        });
        return root;
    }
}
