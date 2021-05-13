package com.example.cookbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookbook.model.CategoryList;
import com.example.cookbook.repositories.CategoryRepository;

import java.util.ArrayList;

public class CategoriesViewModel extends ViewModel {

    CategoryRepository categoryRepository;

    public CategoriesViewModel() {
        categoryRepository = CategoryRepository.getInstance();
    }

    public LiveData<ArrayList<CategoryList>> getCategories()
    {
        return categoryRepository.getSearchedCategories();
    }
}