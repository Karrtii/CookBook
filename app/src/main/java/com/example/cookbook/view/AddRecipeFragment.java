package com.example.cookbook.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookbook.R;
import com.example.cookbook.viewmodel.AddRecipeViewModel;
import com.example.cookbook.viewmodel.RecipesFromCategoryViewModel;

public class AddRecipeFragment extends Fragment {

    private AddRecipeViewModel addRecipeViewModel;

    EditText recipeTitle, ingredients, steps;
    ImageView image;
    Button addImageButton, addRecipeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        addRecipeViewModel  =
                new ViewModelProvider(this).get(AddRecipeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        recipeTitle = root.findViewById(R.id.addRecipeTitle);
        ingredients = root.findViewById(R.id.addIngredients);
        steps = root.findViewById(R.id.addSteps);
        image = root.findViewById(R.id.addImage);
        addImageButton = root.findViewById(R.id.addImageButton);
        addRecipeButton = root.findViewById(R.id.addRecipeButton);


        return root;
    }
}
