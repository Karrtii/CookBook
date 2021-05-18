package com.example.cookbook.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
    private TextView nocategoriesText;

    ArrayList<CategoryList> recipeLists = new ArrayList<>();

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                new ViewModelProvider(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        nocategoriesText = root.findViewById(R.id.noCategoriesText);
        recyclerViewCategories = root.findViewById(R.id.categoriesRecyclerView);
        progressBar = root.findViewById(R.id.categoryProgressBar);

        progressBar.setVisibility(View.VISIBLE);

        recyclerViewCategories.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerViewCategories.hasFixedSize();

        recipeListAdapter = new CategoriesListAdapter(recipeLists, this, this.getContext());
        recyclerViewCategories.setAdapter(recipeListAdapter);

        categoriesViewModel.getCategories().observe(getViewLifecycleOwner(), categoryLists -> {
            progressBar.setVisibility(View.GONE);
            recipeLists.clear();

            recipeLists.addAll(categoryLists);
            recipeListAdapter.notifyDataSetChanged();

            if(!recipeLists.isEmpty())
            {
                nocategoriesText.setVisibility(View.GONE);
            }
            else
                nocategoriesText.setVisibility(View.VISIBLE);
        });

        return root;
    }

    @Override
    public void onListItemClickListener(int position) {
        int recipeNumber = position + 1;

        Bundle bundle = new Bundle();
        bundle.putString("title", recipeListAdapter.recipeLists.get(position).getTitle());

        Log.i("gat", bundle.getString("title"));

        Navigation.findNavController(getView()).navigate(R.id.action_navigation_categories_to_navigation_recipes_from_category, bundle);

        Log.i("TAG", "categories: ");

    }

}