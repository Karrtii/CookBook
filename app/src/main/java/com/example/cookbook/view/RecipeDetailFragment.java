package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.adapter.IngredientsAdapter;
import com.example.cookbook.adapter.RecipesFromCategoryAdapter;
import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.Ingredients;
import com.example.cookbook.viewmodel.CategoriesViewModel;
import com.example.cookbook.viewmodel.RecipeDetailViewModel;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment implements IngredientsAdapter.OnListItemClickListener{

    private RecipeDetailViewModel recipeDetailViewModel;
    private IngredientsAdapter ingredientsAdapter;
    private RecyclerView recyclerView;

    ArrayList<Ingredients> ingredientsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recipeDetailViewModel =
                new ViewModelProvider(this).get(RecipeDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        recyclerView = root.findViewById(R.id.ingredientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        ingredientsAdapter = new IngredientsAdapter(ingredientsList, this, this.getContext());
        recyclerView.setAdapter(ingredientsAdapter);

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;
        Log.i("TAG", "in recipe detail ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}
