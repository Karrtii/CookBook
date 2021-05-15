package com.example.cookbook.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookbook.R;
import com.example.cookbook.viewmodel.AddRecipeViewModel;
import com.example.cookbook.viewmodel.ViewAddedRecipeViewModel;

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


        return root;
    }
}
