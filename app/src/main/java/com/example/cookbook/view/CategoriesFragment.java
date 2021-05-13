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
import com.example.cookbook.adapter.CategoriesListAdapter;
import com.example.cookbook.model.CategoryList;
import com.example.cookbook.viewmodel.CategoriesViewModel;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment implements CategoriesListAdapter.OnListItemClickListener{

    private CategoriesViewModel categoriesViewModel;

    private RecyclerView recyclerViewCategories;
    private CategoriesListAdapter recipeListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerViewCategories = root.findViewById(R.id.categoriesRecyclerView);

        recyclerViewCategories.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewCategories.hasFixedSize();

        ArrayList<CategoryList> recipeLists = new ArrayList<>();
        recipeLists.add(new CategoryList("Fried Chicken", R.drawable.friedchicken));
        recipeLists.add(new CategoryList("French Fries", R.drawable.fries));
        recipeLists.add(new CategoryList("Butter Chicken", R.drawable.butterchicken));
        recipeLists.add(new CategoryList("Ice Cream", R.drawable.icecream));
        recipeLists.add(new CategoryList("Nasi Lemak", R.drawable.nasilemak));
        recipeLists.add(new CategoryList("Pasta", R.drawable.pasta));
        recipeLists.add(new CategoryList("Pineapple Fried Rice", R.drawable.pineapplefriedrice));
        recipeLists.add(new CategoryList("Pizza", R.drawable.pizza));
        recipeLists.add(new CategoryList("Spaghetti", R.drawable.spaghetti));
        recipeLists.add(new CategoryList("Sushi", R.drawable.sushi));
        recipeLists.add(new CategoryList("Beef Wellington", R.drawable.wellington));

        recipeListAdapter = new CategoriesListAdapter(recipeLists, this);
        recyclerViewCategories.setAdapter(recipeListAdapter);

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;
        Log.i("TAG", "categories: ");
        Toast.makeText(getActivity(), "Number is " + recipeNumber, Toast.LENGTH_SHORT).show();
    }
}