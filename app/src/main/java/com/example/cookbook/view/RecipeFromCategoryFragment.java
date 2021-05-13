package com.example.cookbook.view;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookbook.R;
import com.example.cookbook.viewmodel.RecipesFromCategoryViewModel;

public class RecipeFromCategoryFragment extends Fragment {

    private RecipesFromCategoryViewModel recipesFromCategoryViewModel;

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        recipesFromCategoryViewModel =
                new ViewModelProvider(this).get(RecipesFromCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipes_from_category, container, false);

        textView = root.findViewById(R.id.test);

        String title = getArguments().getString("title");

        textView.setText(title);

        return root;
    }
}
