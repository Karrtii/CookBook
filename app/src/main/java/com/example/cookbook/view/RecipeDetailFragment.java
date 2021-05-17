package com.example.cookbook.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookbook.R;
import com.example.cookbook.adapter.IngredientsAdapter;
import com.example.cookbook.adapter.RecipesFromCategoryAdapter;
import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.Favourite;
import com.example.cookbook.model.Ingredients;
import com.example.cookbook.viewmodel.CategoriesViewModel;
import com.example.cookbook.viewmodel.RecipeDetailViewModel;

import java.util.ArrayList;

public class RecipeDetailFragment extends Fragment implements IngredientsAdapter.OnListItemClickListener{

    private RecipeDetailViewModel recipeDetailViewModel;
    private IngredientsAdapter ingredientsAdapter;
    private RecyclerView recyclerView;

    ArrayList<Ingredients> ingredientsList = new ArrayList<>();

    TextView title, publisher, publisherText, ingredientsText;
    ImageView image;
    Button button;
    ToggleButton favouriteButton;
    private String id;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recipeDetailViewModel =
                new ViewModelProvider(this).get(RecipeDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        recyclerView = root.findViewById(R.id.ingredientsRecyclerView);
        progressBar = root.findViewById(R.id.recipeDetailProgressBar);



        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        ingredientsAdapter = new IngredientsAdapter(ingredientsList, this, this.getContext());
        recyclerView.setAdapter(ingredientsAdapter);

        id = getArguments().getString("recipeId");

        title = root.findViewById(R.id.title);
        publisher = root.findViewById(R.id.publisher);
        image = root.findViewById(R.id.image);
        button = root.findViewById(R.id.button);
        favouriteButton = root.findViewById(R.id.favouriteButton);
        publisherText = root.findViewById(R.id.publisherText);
        ingredientsText = root.findViewById(R.id.ingredientsText);

        CheckFavourite();

        recipeDetailViewModel.getRecipeDetail().observe(getViewLifecycleOwner(), recipeDetail -> {
            progressBar.setVisibility(View.GONE);
            title.setText(recipeDetail.getTitle());
            publisher.setText(recipeDetail.getPublisher());
            Glide.with(this).load(recipeDetail.getImageUrl()).into(image);
            ingredientsList.clear();
            ingredientsList.addAll(recipeDetail.getIngredients());
            ingredientsAdapter.notifyDataSetChanged();

                title.setVisibility(View.VISIBLE);
                publisher.setVisibility(View.VISIBLE);
                image.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                publisherText.setVisibility(View.VISIBLE);
                ingredientsText.setVisibility(View.VISIBLE);
                favouriteButton.setVisibility(View.VISIBLE);

            button.setOnClickListener(v -> {
                String url = recipeDetail.getSourceUrl();
                Intent intent = new Intent((Intent.ACTION_VIEW));
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });

            favouriteButton.setOnClickListener(v -> {
                if(favouriteButton.isChecked()) {
                    recipeDetailViewModel.insert(new Favourite(recipeDetail.getId()));
                    //favouriteButton.setBackgroundDrawable(getResources().getDrawable(R.color.pink));
                }
                else
                {
                    recipeDetailViewModel.delete(recipeDetail.getId());
                }
            });
        });

        recipeDetailViewModel.searchForRecipeDetail(id);


        return root;
    }

    private void CheckFavourite()
    {
        recipeDetailViewModel.getAllFavouriteIds().observe(getViewLifecycleOwner(), favourites -> {
            for (Favourite f : favourites)
            {
                if (f.getId().equals(id))
                {
                    favouriteButton.setChecked(true);
                    break;
                }
                else
                    {
                        favouriteButton.setChecked(false);
                    }
            }
        });
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;
        Log.i("TAG", "in recipe detail ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        publisher.setVisibility(View.GONE);
        image.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        publisherText.setVisibility(View.GONE);
        ingredientsText.setVisibility(View.GONE);
    }
}
